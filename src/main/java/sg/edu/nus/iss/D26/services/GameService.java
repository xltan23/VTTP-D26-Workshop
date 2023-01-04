package sg.edu.nus.iss.D26.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.D26.models.Game;
import sg.edu.nus.iss.D26.repositories.GameRepository;

@Service
public class GameService {
    
    @Autowired
    private GameRepository gameRepo;

    public List<Game> searchGames(Integer limit, Integer offset) {
        return gameRepo.searchGames(limit, offset);
    }

    public Game searchGameByID(Integer gid) {
        return gameRepo.searchGameByID(gid);
    }
}
