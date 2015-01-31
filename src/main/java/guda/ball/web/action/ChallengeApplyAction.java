package guda.ball.web.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import guda.ball.biz.ChallengeApplyBiz;
import guda.ball.dao.domain.ChallengeApplyDO;
import guda.ball.web.form.ChallengeApplyEditForm;
import guda.ball.web.form.ChallengeApplyForm;
import guda.tools.web.page.BaseQuery;
import guda.tools.web.page.BizResult;
import guda.tools.web.util.RequestUtil;


@Controller
public class ChallengeApplyAction {


    @Autowired
    private ChallengeApplyBiz challengeApplyBiz;

    @RequestMapping(value = "challengeApply/list.htm", method = RequestMethod.GET)
    public String list(HttpServletRequest request, ModelMap modelMap) {
        int pageId = RequestUtil.getInt(request, "pageNo");
        int pageSize = RequestUtil.getInt(request, "pageSize");
        BaseQuery baseQuery = new BaseQuery();
        baseQuery.setPageNo(pageId);
        modelMap.put("query",baseQuery);
        BizResult bizResult = challengeApplyBiz.list(baseQuery);
        if (bizResult.success) {
            modelMap.putAll(bizResult.data);
            return "challengeApply/list.vm";
        } else {
            return "common/error.vm";
        }

    }

    @RequestMapping(value = "challengeApply/edit.htm", method = RequestMethod.GET)
    public String edit(HttpServletRequest request, ModelMap modelMap, ChallengeApplyEditForm challengeApplyEditForm,
        BindingResult result, Map<String,Object> model) {
        long id = RequestUtil.getLong(request, "id");
        BizResult bizResult = challengeApplyBiz.detail(id);
        if (bizResult.success) {
            modelMap.putAll(bizResult.data);
            challengeApplyEditForm.initForm(((ChallengeApplyDO)bizResult.data.get("challengeApplyDO")));
            return "challengeApply/edit.vm";
        } else {
            return "common/error.vm";
        }

    }

    @RequestMapping(value = "challengeApply/detail.htm", method = RequestMethod.GET)
    public String detail(HttpServletRequest request, ModelMap modelMap) {
        long id = RequestUtil.getLong(request, "id");
        BizResult bizResult = challengeApplyBiz.detail(id);
        if (bizResult.success) {
            modelMap.putAll(bizResult.data);
            return "challengeApply/detail.vm";
        } else {
            return "common/error.vm";
        }

    }

    @RequestMapping(value = "challengeApply/create.htm", method = RequestMethod.GET)
    public String create(HttpServletRequest request, ModelMap modelMap, ChallengeApplyForm challengeApplyForm,
        BindingResult result, Map<String,Object> model) {
        return "challengeApply/create.vm";
    }

    @RequestMapping(value = "challengeApply/doCreate.htm", method = RequestMethod.POST)
    public String doCreate(HttpServletRequest request, ModelMap modelMap,@Valid  ChallengeApplyForm challengeApplyForm,
        BindingResult result, Map<String,Object> model) {
        if (result.hasErrors()) {
            return "challengeApply/create.vm";
        }
        ChallengeApplyDO challengeApplyDO = challengeApplyForm.toDO();
        BizResult bizResult = challengeApplyBiz.create(challengeApplyDO);
        if (bizResult.success) {
            return "redirect:/challengeApply/list.htm";
        } else {
            return "common/error.vm";
        }

    }

    @RequestMapping(value = "challengeApply/doUpdate.htm", method = RequestMethod.POST)
    public String doUpdate(HttpServletRequest request, HttpServletResponse response,ModelMap modelMap,@Valid ChallengeApplyEditForm challengeApplyEditForm,
        BindingResult result, Map<String,Object> model) {
        if (result.hasErrors()) {
            return "challengeApply/edit.vm";
        }
        ChallengeApplyDO challengeApplyDO = challengeApplyEditForm.toDO();
        BizResult bizResult = challengeApplyBiz.update(challengeApplyDO);
        if (bizResult.success) {
            return "redirect:/challengeApply/list.htm";
        } else {
            return "common/error.vm";
        }

    }

    @RequestMapping(value = "challengeApply/doDelete.htm")
    public String doDelete(HttpServletRequest request, ModelMap modelMap) {
        long id = RequestUtil.getLong(request, "id");
        BizResult bizResult = challengeApplyBiz.delete(id);
        if (bizResult.success) {
            return "challengeApply/list.htm";
        } else {
            return "common/error.vm";
        }

    }



}
