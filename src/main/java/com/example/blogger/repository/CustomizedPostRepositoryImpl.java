package com.example.blogger.repository;//package com.bezkoder.spring.jwt.mongodb.repository;
//
//import com.bezkoder.spring.jwt.mongodb.dto.filter.PostFilter;
//import com.bezkoder.spring.jwt.mongodb.entity.Post;
//import io.micrometer.common.util.StringUtils;
//import org.bson.types.ObjectId;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.core.query.Collation;
//import org.springframework.data.mongodb.core.query.Criteria;
//import org.springframework.data.mongodb.core.query.Query;
//import org.springframework.data.support.PageableExecutionUtils;
//import org.springframework.util.CollectionUtils;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Set;
//import java.util.regex.Pattern;
//import java.util.stream.Collectors;
//
//import static java.util.Locale.ENGLISH;
//
//public class CustomizedPostRepositoryImpl implements CustomizedPostRepository {
//    private final MongoTemplate mongoTemplate;
//
//    public CustomizedPostRepositoryImpl(MongoTemplate mongoTemplate) {
//        this.mongoTemplate = mongoTemplate;
//    }
//
//    @Override
//    public Page<Post> getPostList(PostFilter filter, Pageable pageable) {
//        Query query = buildPostFilterQuery(filter);
//        query.with(pageable);
//
//        return PageableExecutionUtils.getPage(
//                this.mongoTemplate.find(query, Post.class),
//                pageable,
//                () -> this.mongoTemplate.count(query.skip(0).limit(0), Post.class)
//        );
//    }
//
//    private Query buildPostFilterQuery(PostFilter filter) {
//        Query query = new Query();
//
//        query.collation(Collation.of(ENGLISH).strength(Collation.ComparisonLevel.secondary()));
//        if (filter == null) {
//            return query;
//        }
//
//        List<Criteria> criteriaList = new ArrayList<>();
//
//        String text = filter.getText();
//        Set<String> userIds = filter.getUserIds();
//
//        if (StringUtils.isNotBlank(text)) {
//            text = Pattern.quote(text);
//            criteriaList.add(
//                    new Criteria()
//                            .orOperator(
//                                    Criteria.where("title").regex(text, "i"),
//                                    Criteria.where("body").regex(text, "i")
//                            )
//            );
//        }
//
//        if (!CollectionUtils.isEmpty(userIds)){
//            Set<ObjectId> objectIds = userIds.stream().map(ObjectId::new)
//                    .collect(Collectors.toSet());
//            query.addCriteria(Criteria.where("user.$id").in(objectIds));
//        }
//
//        if (!CollectionUtils.isEmpty(criteriaList)) {
//            query.addCriteria(new Criteria().andOperator(criteriaList.toArray(new Criteria[0])));
//        }
//
//        return query;
//    }
//}
