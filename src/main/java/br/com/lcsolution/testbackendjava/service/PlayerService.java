package br.com.lcsolution.testbackendjava.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;

import br.com.lcsolution.testbackendjava.domain.Avengers;
import br.com.lcsolution.testbackendjava.domain.AvengersCodeName;
import br.com.lcsolution.testbackendjava.domain.Player;
import br.com.lcsolution.testbackendjava.domain.StatusPlayer;
import br.com.lcsolution.testbackendjava.repository.PlayerRepository;

@Service
public class PlayerService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PlayerService.class);

	private static final String URL = "https://raw.githubusercontent.com/uolhost/test-backEnd-Java/master/referencias/vingadores.json";
	
	@Autowired
	private PlayerRepository playerRepository;
	
	public ModelAndView registerPlayer(Player request, RedirectAttributes redirectAttributes, ModelAndView modelAndView) throws Exception{
        if (request.isAvangers()){
        	HttpURLConnection connection = getContentURL();
        	InputStream inputStream = connection.getInputStream();
            try (BufferedReader bufferReader = new BufferedReader(new InputStreamReader(inputStream))) {
                Gson gson = new Gson();
                Avengers avengersJson = gson.fromJson(bufferReader, Avengers.class);
                List<AvengersCodeName> avengersCodeName = avengersJson.getAvengers();
                for (AvengersCodeName codeName : avengersCodeName) {
                	LOGGER.info("Checking if the codename is available.");
                	Player playerDataBase = playerRepository.findByCodenameAndPlayerGroupAndStatus(codeName.getCodinome(), request.getPlayerGroup(), StatusPlayer.ACTIVE);
					if (Objects.isNull(playerDataBase)){
						createPlayer(codeName,request);
						redirectAttributes.addFlashAttribute("success", "Registro salvo com sucesso!");
						return modelAndView;
					}
				}
                redirectAttributes.addFlashAttribute("error", "Não há codinomes disponivel para esse grupo.");
                return modelAndView;
            } finally {
                connection.disconnect();
            }
		}
		return modelAndView;
	}

	private HttpURLConnection getContentURL() throws IOException {
        LOGGER.info("Consulting url: " + URL);
    	URL url = new URL(URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");
        return conn;
	}

	private void createPlayer(AvengersCodeName codeName, Player request) {
		Player player = new Player();
		player.setCodename(codeName.getCodinome());
		player.setEmail(request.getEmail());
		player.setName(request.getName());
		player.setPlayerGroup(request.getPlayerGroup());
		player.setStatus(StatusPlayer.ACTIVE);
		player.setTelephone(request.getTelephone());
		playerRepository.save(player);
		LOGGER.info("Player: "+player.getName()+" created with success.");
	}

	public List<Player> getPlayers() {
		return playerRepository.findAll();
	}

}