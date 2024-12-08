package database.doktalk.domain.discussionreview.service;

import database.doktalk.common.global.exception.CustomApiException;
import database.doktalk.common.global.exception.ErrorCode;
import database.doktalk.domain.discussion.entity.Discussion;
import database.doktalk.domain.discussion.repository.DiscussionRepository;
import database.doktalk.domain.discussionreview.dto.request.DiscussionReviewRequest;
import database.doktalk.domain.discussionreview.dto.response.DiscussionReviewIdResponse;
import database.doktalk.domain.discussionreview.dto.response.DiscussionReviewResponse;
import database.doktalk.domain.discussionreview.entity.DiscussionReview;
import database.doktalk.domain.discussionreview.mapper.DiscussionReviewMapper;
import database.doktalk.domain.discussionreview.repository.DiscussionReviewRepository;
import database.doktalk.domain.user.entity.User;
import database.doktalk.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DiscussionReviewServiceImpl implements DiscussionReviewService{
    private final DiscussionReviewRepository discussionReviewRepository;
    private final DiscussionReviewMapper discussionReviewMapper;
    private final UserRepository userRepository;
    private final DiscussionRepository discussionRepository;



    @Override
    public DiscussionReviewIdResponse createDiscussionReview(DiscussionReviewRequest request){
        Discussion discussion = discussionRepository.findById(request.getDiscussionId()).orElseThrow(()->new CustomApiException(ErrorCode.DISCUSSION_NOT_FOUND));
        User user =userRepository.findById(request.getUserId()).orElseThrow(()->new CustomApiException(ErrorCode.USER_NOT_FOUND));
        DiscussionReview newDisCussionReview = discussionReviewRepository.save(
                discussionReviewMapper.toDiscussionReview(request , user, discussion)
        );

        return new DiscussionReviewIdResponse(newDisCussionReview.getId());
    }

    @Override
    public List<DiscussionReviewResponse> getDiscussionReviews(Long discussionId){

        return discussionReviewRepository
                .findByDiscussionId(discussionId)
                .stream()
                .map(discussionReviewMapper::toDiscussionReviewResponse)
                .toList();
    }
}
