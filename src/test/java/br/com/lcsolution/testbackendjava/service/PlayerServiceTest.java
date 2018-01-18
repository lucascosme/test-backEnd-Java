package br.com.lcsolution.testbackendjava.service;

import java.lang.reflect.Field;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import br.com.lcsolution.testbackendjava.domain.Player;
import br.com.lcsolution.testbackendjava.domain.StatusPlayer;
import br.com.lcsolution.testbackendjava.repository.PlayerRepository;

public class PlayerServiceTest {
	
	private PlayerService playerService;
	
	@Before
	public void setUp() throws Exception{
		playerService = new PlayerService();
	}
	
	@Test
	public void testClassAnnotations() {
		Class<? extends PlayerService> clazz = playerService.getClass();
		Assert.assertTrue(clazz.isAnnotationPresent(Service.class));
	}
	
	@Test
	public void testWhenPlayerGroupIsAvangers() throws Exception{
		Player request = createPlayer();
		RedirectAttributesModelMap redirectAttributes = new RedirectAttributesModelMap();
		ModelAndView modelAndView = new ModelAndView();
		
		Player player = null;
		PlayerRepository playerRepositoryMock = createPlayerRepositoryMock(request.getPlayerGroup(), request.getCodename(), player, request);
		
		playerService.registerPlayer(request, redirectAttributes, modelAndView);
		EasyMock.verify(playerRepositoryMock);
	}

	private PlayerRepository createPlayerRepositoryMock(String playerGroup, String codeName, Player player, Player request) {
		PlayerRepository mock = EasyMock.createMock(PlayerRepository.class);
		EasyMock.expect(mock.findByCodenameAndPlayerGroupAndStatus(codeName, playerGroup, StatusPlayer.ACTIVE)).andReturn(player);
		EasyMock.expect(mock.save(request)).andReturn(new Player());
		EasyMock.replay(mock);
		Field field = ReflectionUtils.findField(playerService.getClass(), "playerRepository");
		ReflectionUtils.makeAccessible(field);
		ReflectionUtils.setField(field, playerService, mock);
		return mock;
	}

	private Player createPlayer() {
		Player player = new Player();
		player.setCodename("Hulk");
		player.setEmail("email@teste.com");
		player.setName("name");
		player.setPlayerGroup("avangers");
		player.setStatus(StatusPlayer.ACTIVE);
		player.setTelephone("11999999999");
		return player;
	}

}
