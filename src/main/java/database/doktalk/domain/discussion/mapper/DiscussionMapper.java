package database.doktalk.domain.discussion.mapper;

import database.doktalk.domain.discussion.dto.request.DiscussionRequest;
import database.doktalk.domain.discussion.dto.response.DiscussionDetailResponse;
import database.doktalk.domain.discussion.entity.Discussion;
import database.doktalk.domain.user.entity.User;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
public class DiscussionMapper {

    public Discussion toDiscussion(DiscussionRequest request, User user){
        return Discussion.builder()
                .title(request.getTitle())
                .bookTitle(request.getBookTitle())
                .author(request.getAuthor())
                .content(request.getContent())
                .publisher(request.getPublisher())
                .user(user)
                .build();
    }

    public DiscussionDetailResponse toDisCussionDetailResponse(Discussion discussion){
        return DiscussionDetailResponse.builder()
                .discussionId(discussion.getId())
                .title(discussion.getTitle())
                .bookTitle(discussion.getBookTitle())
                .author(discussion.getAuthor())
                .content(discussion.getContent())
                .publisher(discussion.getPublisher())
                .build();
    }
}
