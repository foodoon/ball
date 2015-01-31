package guda.ball.biz.entity;

import guda.ball.dao.domain.CourtSiteSectionDO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by foodoon on 2015/1/31.
 */
public class OpenTemplateDemo {

    public List<OpenTemplate> list;

    public Map<Integer,List<CourtSiteSectionDO>> map ;
    public OpenTemplateDemo(){

    }

    public Map<Integer, List<CourtSiteSectionDO>> getMap() {
        return map;
    }

    public void setMap(Map<Integer, List<CourtSiteSectionDO>> map) {
        this.map = map;
    }

    public void init(){
        list = new ArrayList<OpenTemplate>();
        map = new HashMap<Integer,List<CourtSiteSectionDO>>();
        OpenTemplate openTemplate = new OpenTemplate();
        openTemplate.setWeek(0);
        openTemplate.setTimeSection(initSection());
        list.add(openTemplate);
        map.put(0,openTemplate.getTimeSection());
        openTemplate = new OpenTemplate();
        openTemplate.setWeek(1);
        openTemplate.setTimeSection(initSection());
        list.add(openTemplate);
        map.put(1,openTemplate.getTimeSection());

        openTemplate = new OpenTemplate();
        openTemplate.setWeek(2);
        openTemplate.setTimeSection(initSection());
        list.add(openTemplate);
        map.put(2,openTemplate.getTimeSection());

        openTemplate = new OpenTemplate();
        openTemplate.setWeek(3);
        openTemplate.setTimeSection(initSection());
        list.add(openTemplate);
        map.put(3,openTemplate.getTimeSection());

        openTemplate = new OpenTemplate();
        openTemplate.setWeek(4);
        openTemplate.setTimeSection(initSection());
        list.add(openTemplate);
        map.put(4,openTemplate.getTimeSection());

        openTemplate = new OpenTemplate();
        openTemplate.setWeek(5);
        openTemplate.setTimeSection(initSection());
        list.add(openTemplate);
        map.put(5,openTemplate.getTimeSection());

        openTemplate = new OpenTemplate();
        openTemplate.setWeek(6);
        openTemplate.setTimeSection(initSection());
        list.add(openTemplate);
        map.put(6,openTemplate.getTimeSection());


    }

    public List<CourtSiteSectionDO> initSection(){
        List<CourtSiteSectionDO> timeSectionList = new ArrayList<CourtSiteSectionDO>();
        CourtSiteSectionDO courtSiteSectionDO = new CourtSiteSectionDO();
        courtSiteSectionDO.setPrice(2500L);
        courtSiteSectionDO.setTimeInfo("08:00:00-10:00:00");
        courtSiteSectionDO.setStatus(0);
        timeSectionList.add(courtSiteSectionDO);

        courtSiteSectionDO = new CourtSiteSectionDO();
        courtSiteSectionDO.setPrice(2500L);
        courtSiteSectionDO.setTimeInfo("10:00:00-12:00:00");
        courtSiteSectionDO.setStatus(0);
        timeSectionList.add(courtSiteSectionDO);

        courtSiteSectionDO = new CourtSiteSectionDO();
        courtSiteSectionDO.setPrice(2500L);
        courtSiteSectionDO.setTimeInfo("13:00:00-15:00:00");
        courtSiteSectionDO.setStatus(0);
        timeSectionList.add(courtSiteSectionDO);

        courtSiteSectionDO = new CourtSiteSectionDO();
        courtSiteSectionDO.setPrice(2500L);
        courtSiteSectionDO.setTimeInfo("15:00:00-17:00:00");
        courtSiteSectionDO.setStatus(0);
        timeSectionList.add(courtSiteSectionDO);

        return timeSectionList;
    }

    public List<OpenTemplate> getList() {
        return list;
    }

    public void setList(List<OpenTemplate> list) {
        this.list = list;
    }
}
