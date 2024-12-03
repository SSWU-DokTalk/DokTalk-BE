package database.doktalk.domain.worldcup.service;

import database.doktalk.domain.worldcup.dto.request.NextRoundRequest;
import database.doktalk.domain.worldcup.dto.request.WorldCupRequest;
import database.doktalk.domain.worldcup.dto.response.MatchIdResponse;
import database.doktalk.domain.worldcup.dto.response.RoundResponse;
import database.doktalk.domain.worldcup.dto.response.WorldCupIdResponse;

public interface WorldCupService {
    WorldCupIdResponse createWorldCup(WorldCupRequest request);
    RoundResponse createNextRound(NextRoundRequest request);
    MatchIdResponse createMatch(int roundNumber, Long bookId1, Long bookId2, Long worldCup);


}
