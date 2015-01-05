package guda.ball.web.action;

import guda.tools.web.page.BaseQuery;
import guda.tools.web.page.BizResult;
import guda.tools.web.util.RequestUtil;
import guda.ball.biz.ChallengeBiz;
import guda.ball.dao.domain.ChallengeDO;
import guda.ball.web.form.ChallengeEditForm;
import guda.ball.web.form.ChallengeForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Map;


@Controller
public class ChallengeAction {


    @Autowired
    private ChallengeBiz challengeBiz;

    @RequestMapping(value = "challenge/list.htm", method = RequestMethod.GET)
    public String list(HttpServletRequest request, ModelMap modelMap) {
        int pageId = RequestUtil.getInt(request, "pageNo");
        int pageSize = RequestUtil.getInt(request, "pageSize");
        BaseQuery baseQuery = new BaseQuery();
        baseQuery.setPageNo(pageId);
        modelMap.put("query",baseQuery);
        BizResult bizResult = challengeBiz.list(baseQuery);
        if (bizResult.success) {
            modelMap.putAll(bizResult.data);
            return "challenge/list.vm";
        } else {
            return "common/error.vm";
        }

    }

    @RequestMapping(value = "challenge/edit.htm", method = RequestMethod.GET)
    public String edit(HttpServletRequest request, ModelMap modelMap, ChallengeEditForm challengeEditForm,
        BindingResult result, Map<String,Object> model) {
        int id = RequestUtil.getInt(request, "id");
        BizResult bizResult = challengeBiz.detail(id);
        if (bizResult.success) {
            modelMap.putAll(bizResult.data);
            challengeEditForm.initForm(((ChallengeDO)bizResult.data.get("challengeDO")));
            return "challenge/edit.vm";
        } else {
            return "common/error.vm";
        }

    }

    @RequestMapping(value = "challenge/detail.htm", method = RequestMethod.GET)
    public String detail(HttpServletRequest request, ModelMap modelMap) {
        int id = RequestUtil.getInt(request, "id");
        BizResult bizResult = challengeBiz.detail(id);
        if (bizResult.success) {
            modelMap.putAll(bizResult.data);
            return "challenge/detail.vm";
        } else {
            return "common/error.vm";
        }

    }

    @RequestMapping(value = "challenge/create.htm", method = RequestMethod.GET)
    public String create(HttpServletRequest request, ModelMap modelMap, ChallengeForm challengeForm,
        BindingResult result, Map<String,Object> model) {
        return "challenge/create.vm";
    }

    @RequestMapping(value = "challenge/doCreate.htm", method = RequestMethod.POST)
    public String doCreate(HttpServletRequest request, ModelMap modelMap,@Valid  ChallengeForm challengeForm,
        BindingResult result, Map<String,Object> model) {
        if (result.hasErrors()) {
            return "challenge/create.vm";
        }
        ChallengeDO challengeDO = challengeForm.toDO();
        BizResult bizResult = challengeBiz.create(challengeDO);
        if (bizResult.success) {
            return "redirect:/challenge/list.htm";
        } else {
            return "common/error.vm";
        }

    }

    @RequestMapping(value = "challenge/doUpdate.htm", method = RequestMethod.POST)
    public String doUpdate(HttpServletRequest request, HttpServletResponse response,ModelMap modelMap,@Valid ChallengeEditForm challengeEditForm,
        BindingResult result, Map<String,Object> model) {
        if (result.hasErrors()) {
            return "challenge/edit.vm";
        }
        ChallengeDO challengeDO = challengeEditForm.toDO();
        BizResult bizResult = challengeBiz.update(challengeDO);
        if (bizResult.success) {
            return "redirect:/challenge/list.htm";
        } else {
            return "common/error.vm";
        }

    }

    @RequestMapping(value = "challenge/doDelete.htm")
    public String doDelete(HttpServletRequest request, ModelMap modelMap) {
        int id = RequestUtil.getInt(request, "id");
        BizResult bizResult = challengeBiz.delete(id);
        if (bizResult.success) {
            return "challenge/list.htm";
        } else {
            return "common/error.vm";
        }

    }



}
