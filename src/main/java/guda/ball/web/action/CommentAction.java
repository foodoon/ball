package guda.ball.web.action;

import guda.tools.web.page.BaseQuery;
import guda.tools.web.page.BizResult;
import guda.tools.web.util.RequestUtil;
import guda.ball.biz.CommentBiz;
import guda.ball.dao.domain.CommentDO;
import guda.ball.web.form.CommentEditForm;
import guda.ball.web.form.CommentForm;
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
public class CommentAction {


    @Autowired
    private CommentBiz commentBiz;

    @RequestMapping(value = "comment/list.htm", method = RequestMethod.GET)
    public String list(HttpServletRequest request, ModelMap modelMap) {
        int pageId = RequestUtil.getInt(request, "pageNo");
        int pageSize = RequestUtil.getInt(request, "pageSize");
        BaseQuery baseQuery = new BaseQuery();
        baseQuery.setPageNo(pageId);
        modelMap.put("query",baseQuery);
        BizResult bizResult = commentBiz.list(baseQuery);
        if (bizResult.success) {
            modelMap.putAll(bizResult.data);
            return "comment/list.vm";
        } else {
            return "common/error.vm";
        }

    }

    @RequestMapping(value = "comment/edit.htm", method = RequestMethod.GET)
    public String edit(HttpServletRequest request, ModelMap modelMap, CommentEditForm commentEditForm,
        BindingResult result, Map<String,Object> model) {
        int id = RequestUtil.getInt(request, "id");
        BizResult bizResult = commentBiz.detail(id);
        if (bizResult.success) {
            modelMap.putAll(bizResult.data);
            commentEditForm.initForm(((CommentDO)bizResult.data.get("commentDO")));
            return "comment/edit.vm";
        } else {
            return "common/error.vm";
        }

    }

    @RequestMapping(value = "comment/detail.htm", method = RequestMethod.GET)
    public String detail(HttpServletRequest request, ModelMap modelMap) {
        int id = RequestUtil.getInt(request, "id");
        BizResult bizResult = commentBiz.detail(id);
        if (bizResult.success) {
            modelMap.putAll(bizResult.data);
            return "comment/detail.vm";
        } else {
            return "common/error.vm";
        }

    }

    @RequestMapping(value = "comment/create.htm", method = RequestMethod.GET)
    public String create(HttpServletRequest request, ModelMap modelMap, CommentForm commentForm,
        BindingResult result, Map<String,Object> model) {
        return "comment/create.vm";
    }

    @RequestMapping(value = "comment/doCreate.htm", method = RequestMethod.POST)
    public String doCreate(HttpServletRequest request, ModelMap modelMap,@Valid  CommentForm commentForm,
        BindingResult result, Map<String,Object> model) {
        if (result.hasErrors()) {
            return "comment/create.vm";
        }
        CommentDO commentDO = commentForm.toDO();
        BizResult bizResult = commentBiz.create(commentDO);
        if (bizResult.success) {
            return "redirect:/comment/list.htm";
        } else {
            return "common/error.vm";
        }

    }

    @RequestMapping(value = "comment/doUpdate.htm", method = RequestMethod.POST)
    public String doUpdate(HttpServletRequest request, HttpServletResponse response,ModelMap modelMap,@Valid CommentEditForm commentEditForm,
        BindingResult result, Map<String,Object> model) {
        if (result.hasErrors()) {
            return "comment/edit.vm";
        }
        CommentDO commentDO = commentEditForm.toDO();
        BizResult bizResult = commentBiz.update(commentDO);
        if (bizResult.success) {
            return "redirect:/comment/list.htm";
        } else {
            return "common/error.vm";
        }

    }

    @RequestMapping(value = "comment/doDelete.htm")
    public String doDelete(HttpServletRequest request, ModelMap modelMap) {
        int id = RequestUtil.getInt(request, "id");
        BizResult bizResult = commentBiz.delete(id);
        if (bizResult.success) {
            return "comment/list.htm";
        } else {
            return "common/error.vm";
        }

    }



}
