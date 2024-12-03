package database.doktalk.domain.worldcup.service;

import database.doktalk.common.global.exception.CustomApiException;
import database.doktalk.common.global.exception.ErrorCode;
import database.doktalk.domain.book.entity.Book;
import database.doktalk.domain.book.repository.BookRepository;
import database.doktalk.domain.worldcup.dto.request.NextRoundRequest;
import database.doktalk.domain.worldcup.dto.request.WorldCupRequest;
import database.doktalk.domain.worldcup.dto.response.BookSummaryResponse;
import database.doktalk.domain.worldcup.dto.response.RoundResponse;
import database.doktalk.domain.worldcup.dto.response.MatchIdResponse;
import database.doktalk.domain.worldcup.dto.response.WorldCupIdResponse;
import database.doktalk.domain.worldcup.entity.WorldCup;
import database.doktalk.domain.worldcup.entity.WorldCupMatch;
import database.doktalk.domain.worldcup.mapper.WorldCupMapper;
import database.doktalk.domain.worldcup.repository.WorldCupMatchRepository;
import database.doktalk.domain.worldcup.repository.WorldCupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorldCupServiceImpl implements WorldCupService {

    private final WorldCupMapper worldCupMapper;
    private final WorldCupRepository worldCupRepository;
    private final WorldCupMatchRepository worldCupMatchRepository;
    private final BookRepository bookRepository;
    private final int ROUND_NUMBER_16 = 16;

    /*
     * 월드컵 생성
     */
    @Override
    public WorldCupIdResponse createWorldCup(WorldCupRequest request) {
        List<Long> bookIds = request.getBookIds();

        WorldCup newWorldCup = worldCupMapper.toWorldCup(request);
        worldCupRepository.save(newWorldCup);

        for (int i = 0; i < bookIds.size(); i += 2) {
            Long bookId1 = bookIds.get(i);
            Long bookId2 = bookIds.get(i + 1);
            createMatch(ROUND_NUMBER_16, bookId1, bookId2, newWorldCup.getId());
        }

        return new WorldCupIdResponse(newWorldCup.getId());
    }

    /*
     * 월드컵 라운드 생성 ex) 8강 ,4강 , 2강
     */
    @Override
    public RoundResponse createNextRound(NextRoundRequest request){
        List<Book> books = bookRepository.findAllById(request.getBookIds());

        List<BookSummaryResponse> bookInfos = books.stream()
                .map(worldCupMapper::toBookSummaryResponse)
                .toList();
        WorldCup worldCup = worldCupRepository.findById(request.getWorldCupId()).orElseThrow(()-> new CustomApiException(ErrorCode.WORLD_CUP_NOT_FOUND));

        return worldCupMapper.toRoundResponse(worldCup.getSubject(), bookInfos);
    }


    /*
     * 월드컵 경기 생성
     */

    public MatchIdResponse createMatch(int roundNumber, Long bookId1, Long bookId2, Long worldCupId) {
        WorldCup worldCup = worldCupRepository.findById(worldCupId).orElseThrow(()-> new CustomApiException(ErrorCode.WORLD_CUP_NOT_FOUND));
        Book book1 = bookRepository.findById(bookId1).orElseThrow(()->new CustomApiException(ErrorCode.BOOK_NOT_FOUND));
        Book book2 = bookRepository.findById(bookId2).orElseThrow(()->new CustomApiException(ErrorCode.BOOK_NOT_FOUND));
        WorldCupMatch newMatch = worldCupMapper.toWorldCupMatch(roundNumber, book1, book2, worldCup);
        WorldCupMatch savedMatch = worldCupMatchRepository.save(newMatch);

        // savedMatch의 ID를 반환
        return new MatchIdResponse(savedMatch.getId());
    }
}




