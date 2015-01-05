package guda.ball.web.action;
import guda.tools.web.page.BaseQuery;
import guda.tools.web.page.BizResult;
import guda.tools.web.util.RequestUtil;
import guda.ball.biz.CourtBiz;
import guda.ball.dao.domain.CourtDO;
import guda.ball.web.form.CourtEditForm;
import guda.ball.web.form.CourtForm;
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
public class CourtAction {


    @Autowired
    private CourtBiz courtBiz;

    @RequestMapping(value = "court/list.htm", method = RequestMethod.GET)
    public String list(HttpServletRequest request, ModelMap modelMap) {
        int pageId = RequestUtil.getInt(request, "pageNo");
        int pageSize = RequestUtil.getInt(request, "pageSize");
        BaseQuery baseQuery = new BaseQuery();
        baseQuery.setPageNo(pageId);
        modelMap.put("query",baseQuery);
        BizResult bizResult = courtBiz.list(baseQuery);
        if (bizResult.success) {
            modelMap.putAll(bizResult.data);
            return "court/list.vm";
        } else {
            return "common/error.vm";
        }

    }

    @RequestMapping(value = "court/edit.htm", method = RequestMethod.GET)
    public String edit(HttpServletRequest request, ModelMap modelMap, CourtEditForm courtEditForm,
        BindingResult result, Map<String,Object> model) {
        int id = RequestUtil.getInt(request, "id");
        BizResult bizResult = courtBiz.detail(id);
        if (bizResult.success) {
            modelMap.putAll(bizResult.data);
            courtEditForm.initForm(((CourtDO)bizResult.data.get("courtDO")));
            return "court/edit.vm";
        } else {
            return "common/error.vm";
        }

    }

    @RequestMapping(value = "court/detail.htm", method = RequestMethod.GET)
    public String detail(HttpServletRequest request, ModelMap modelMap) {
        int id = RequestUtil.getInt(request, "id");
        BizResult bizResult = courtBiz.detail(id);
        if (bizResult.success) {
            modelMap.putAll(bizResult.data);
            return "court/detail.vm";
        } else {
            return "common/error.vm";
        }

    }

    @RequestMapping(value = "court/create.htm", method = RequestMethod.GET)
    public String create(HttpServletRequest request, ModelMap modelMap, CourtForm courtForm,
        BindingResult result, Map<String,Object> model) {
        return "court/create.vm";
    }

    @RequestMapping(value = "court/doCreate.htm", method = RequestMethod.POST)
    public String doCreate(HttpServletRequest request, ModelMap modelMap,@Valid CourtForm courtForm,
        BindingResult result, Map<String,Object> model) {
        if (result.hasErrors()) {
            return "court/create.vm";
        }
        CourtDO courtDO = courtForm.toDO();
        BizResult bizResult = courtBiz.create(courtDO);
        if (bizResult.success) {
            return "redirect:/court/list.htm";
        } else {
            return "common/error.vm";
        }

    }

    @RequestMapping(value = "court/doUpdate.htm", method = RequestMethod.POST)
    public String doUpdate(HttpServletRequest request, HttpServletResponse response,ModelMap modelMap,@Valid CourtEditForm courtEditForm,
        BindingResult result, Map<String,Object> model) {
        if (result.hasErrors()) {
            return "court/edit.vm";
        }
        CourtDO courtDO = courtEditForm.toDO();
        BizResult bizResult = courtBiz.update(courtDO);
        if (bizResult.success) {
            return "redirect:/court/list.htm";
        } else {
            return "common/error.vm";
        }

    }

    @RequestMapping(value = "court/doDelete.htm")
    public String doDelete(HttpServletRequest request, ModelMap modelMap) {
        int id = RequestUtil.getInt(request, "id");
        BizResult bizResult = courtBiz.delete(id);
        if (bizResult.success) {
            return "redirect:/court/list.htm";
        } else {
            return "common/error.vm";
        }

    }



}
