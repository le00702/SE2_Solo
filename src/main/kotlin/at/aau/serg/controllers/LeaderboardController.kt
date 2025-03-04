package at.aau.serg.controllers

import at.aau.serg.models.GameResult
import at.aau.serg.services.GameResultService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/leaderboard")
class LeaderboardController(
    private val gameResultService: GameResultService
) {

    @GetMapping
    fun getLeaderboard(): List<GameResult> =
        gameResultService.getGameResults().sortedWith(compareBy({ -it.score }, { it.timeInSeconds }, { it.id }))
        //instead of the tiebreaker being the smallest id, the completion time is used
        //gameResultService.getGameResults().sortedWith(compareBy({ -it.score }, { it.id })) #Old implementation

}