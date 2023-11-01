package com.example.blogger.repository;//package com.bezkoder.spring.jwt.mongodb.repository;
//
//import com.bezkoder.spring.jwt.mongodb.dto.filter.UserFilter;
//import com.bezkoder.spring.jwt.mongodb.entity.User;
//import org.apache.commons.lang3.StringUtils;
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
//import static com.bezkoder.spring.jwt.mongodb.constants.Constants.*;
//import static java.util.Locale.ENGLISH;
//
//public class CustomizedUserRepositoryImpl implements CustomizedUserRepository {
//
//    private final MongoTemplate mongoTemplate;
//
//    public CustomizedUserRepositoryImpl(MongoTemplate mongoTemplate) {
//        this.mongoTemplate = mongoTemplate;
//    }
//
//    @Override
//    public Page<User> getUserList(UserFilter filter, Pageable pageable) {
//        Query query = buildUserFilterQuery(filter);
//        query.with(pageable);
//
//        return PageableExecutionUtils.getPage(
//                this.mongoTemplate.find(query, User.class),
//                pageable,
//                () -> this.mongoTemplate.count(query.skip(0).limit(0), User.class)
//        );
//    }
//
//    private Query buildUserFilterQuery(UserFilter filter) {
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
//        Set<String> roleIds = filter.getRoleIds();
//
//        if (StringUtils.isNotBlank(text)) {
//            text = Pattern.quote(text);
//            criteriaList.add(
//                    new Criteria()
//                            .orOperator(
//                                    Criteria.where(USER_NAME).regex(text, "i"),
//                                    Criteria.where(LOGIN).regex(text, "i"),
//                                    Criteria.where(EMAIL).regex(text, "i")
//                            )
//            );
//        }
//
//        if (!CollectionUtils.isEmpty(roleIds)) {
//            List<ObjectId> ids = roleIds.stream().map(ObjectId::new).collect(Collectors.toList());
//            criteriaList.add(Criteria.where(ROLE + REF_ID).in(ids));
//        }
//
//        if (!CollectionUtils.isEmpty(criteriaList)) {
//            query.addCriteria(new Criteria().andOperator(criteriaList.toArray(new Criteria[0])));
//        }
//
//        return query;
//    }
//}
