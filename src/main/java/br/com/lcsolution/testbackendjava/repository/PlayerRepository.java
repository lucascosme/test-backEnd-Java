package br.com.lcsolution.testbackendjava.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.lcsolution.testbackendjava.domain.Player;
import br.com.lcsolution.testbackendjava.domain.StatusPlayer;

public interface PlayerRepository extends JpaRepository<Player, Long> {
	
	Player findByCodenameAndPlayerGroupAndStatus(String codeName, String playerGroup, StatusPlayer statusPlayer);

}
