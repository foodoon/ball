package guda.ball.biz.entity;

import guda.ball.dao.domain.TeamDO;
import guda.ball.dao.domain.UserDO;
import guda.ball.util.enums.CourtTypeEnum;

/**
 * Created by foodoon on 2015/1/21.
 */
public class UserVO extends UserDO {


    private String groundTypeOfEnjoyCN;

    private TeamDO team;

    public UserVO() {

    }

    public String getGroundTypeOfEnjoyCN() {
        return groundTypeOfEnjoyCN;
    }

    public void setGroundTypeOfEnjoyCN(String groundTypeOfEnjoyCN) {
        this.groundTypeOfEnjoyCN = groundTypeOfEnjoyCN;
    }

    public UserVO(UserDO userDO) {
        setGroundTypeOfEnjoy(userDO.getGroundTypeOfEnjoy());
        setAddress(userDO.getAddress());
        setEmail(userDO.getEmail());
        setPhone(userDO.getPhone());
        setGroundOfDaily(userDO.getGroundOfDaily());
        setImg(userDO.getImg());
        setUserName(userDO.getUserName());
        setRealName(userDO.getRealName());
        setSpecial(userDO.getSpecial());
        setId(userDO.getId());
        this.setPhone(userDO.getPhone());
        this.setExpertFooter(userDO.getExpertFooter());
        this.setExpertLocation(userDO.getExpertLocation());
        this.setHometown(userDO.getHometown());
        this.setHeight(userDO.getHeight());
        this.setWeight(userDO.getWeight());
        this.setSign(userDO.getSign());
        CourtTypeEnum byValue = CourtTypeEnum.getByValue(getGroundTypeOfEnjoy());
        if (byValue != null) {
            this.setGroundTypeOfEnjoy(byValue.msg);
        }


    }

    public TeamDO getTeam() {
        return team;
    }

    public void setTeam(TeamDO team) {
        this.team = team;
    }
}
