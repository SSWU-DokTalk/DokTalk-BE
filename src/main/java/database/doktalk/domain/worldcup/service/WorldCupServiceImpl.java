package database.doktalk.domain.worldcup.service;

import database.doktalk.common.global.exception.CustomApiException;
import database.doktalk.common.global.exception.ErrorCode;
import database.doktalk.domain.book.entity.Book;
import database.doktalk.domain.book.repository.BookRepository;
import database.doktalk.domain.worldcup.dto.request.NextRoundRequest;
import database.doktalk.domain.worldcup.dto.request.WorldCupRequest;
import database.doktalk.domain.worldcup.dto.response.BookSummaryResponse;
import database.doktalk.domain.worldcup.dto.response.WorldCupResponse;
import database.doktalk.domain.worldcup.dto.response.WorldCupSummaryResponse;
import database.doktalk.domain.worldcup.entity.WorldCup;
import database.doktalk.domain.worldcup.entity.WorldCupMatch;
import database.doktalk.domain.worldcup.mapper.WorldCupMapper;
import database.doktalk.domain.worldcup.repository.WorldCupMatchRepository;
import database.doktalk.domain.worldcup.repository.WorldCupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class WorldCupServiceImpl implements WorldCupService {
    private final WorldCupRepository worldCupRepository;
    private final WorldCupMapper worldCupMapper;

    /*
     * 전체 월드컵 조회하기
     */
    @Override
    public List<WorldCupSummaryResponse> getWorldCups() {

        return worldCupRepository.findAll()
                .stream()
                .map(worldCupMapper::toWorldCupSummaryResponse)
                .toList();
    }

    /*
     * 월드컵 세부사항 조회하기
     */
    @Override
    public WorldCupResponse getWorldCupDetail(Long worldCupId) {
        WorldCup worldCup = worldCupRepository.findById(worldCupId).orElseThrow(() -> new CustomApiException(ErrorCode.WORLD_CUP_NOT_FOUND));
        List<WorldCupMatch> matches = worldCup.getMatches();
        List<BookSummaryResponse> books = matches.stream()
                .flatMap(match -> Stream.of(match.getBook1(), match.getBook2())) // book1, book2를 추출
                .map(worldCupMapper::toBookSummaryResponse)
                .toList();

        return new WorldCupResponse(worldCup.getSubject(), books);
    }
}




