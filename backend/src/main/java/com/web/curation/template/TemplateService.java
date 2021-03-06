package com.web.curation.template;

import com.web.curation.error.CustomException;
import com.web.curation.error.ErrorCode;
import com.web.curation.member.User;
import com.web.curation.member.UserDao;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class TemplateService {
    private TemplateDao templateDao;
    private UserDao userDao;

    public void createTemplate(Template template){
        log.info(template.getT_title() + " 템플릿 생성");
        template.setSurvey(new ArrayList<>());
        template = templateDao.save(template);

        User user  = userDao.findById(template.getWriter())
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));

        user.getTemplate().add(template.getTid());
        userDao.save(user);
    }

    public List<Template> getUserTemplates(String uid){
        log.info(uid + " 회원의 템플릿 리스트 호출");

        List<Template> list = new ArrayList<>();
        User user  = userDao.findById(uid)
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));

        for(String tid : user.getTemplate()){
            Template template = templateDao.findById(tid)
                    .orElseThrow(() -> new CustomException(ErrorCode.TEMPLATE_NOT_FOUND));
            list.add(template);
        }

        Collections.reverse(list);
        return list;
    }

    public Template getTemplate(String tid){
        log.info(tid + " 템플릿 호출");

        Template template = templateDao.findById(tid)
                .orElseThrow(() -> new CustomException(ErrorCode.TEMPLATE_NOT_FOUND));
        if(template.isDeleted()) throw new CustomException(ErrorCode.TEMPLATE_NOT_FOUND);

        return template;
    }

    public void deleteTemplate(String tid, String uid){
        log.info(uid + " 회원의 " + tid + " 템플릿 삭제");
        User user  = userDao.findById(uid)
                .orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));

        user.getTemplate().remove(tid);
        userDao.save(user);

        Template template = templateDao.findById(tid)
                .orElseThrow(() -> new CustomException(ErrorCode.TEMPLATE_NOT_FOUND));

        template.setDeleted(true);
        templateDao.save(template);
}
}
