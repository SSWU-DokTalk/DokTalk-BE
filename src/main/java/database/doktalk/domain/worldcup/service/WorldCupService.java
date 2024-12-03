package database.doktalk.domain.worldcup.service;

import database.doktalk.domain.worldcup.dto.response.WorldCupResponse;
import database.doktalk.domain.worldcup.dto.response.WorldCupSummaryResponse;

import java.util.List;

public interface WorldCupService {
    List<WorldCupSummaryResponse> getWorldCups();
    WorldCupResponse getWorldCupDetail(Long worldCupId);
}
