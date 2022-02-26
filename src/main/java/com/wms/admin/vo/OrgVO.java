package com.wms.admin.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.TreeSet;

@Data
public class OrgVO implements Comparable<OrgVO> {
    /**
     * 主键ID
     */
    private String id;

    /**
     * 组织名称
     */
    @NotBlank(message = "组织名称不能为空")
    private String orgName;

    /**
     * 组织代码
     */
    @NotBlank(message = "组织代码不能为空")
    private String orgCode;

    /**
     * 父ID
     */
    private String parentId;

    /**
     * 组织层级路径
     */
    private String levelPath;

    /**
     * 组织层级
     */
    private Integer levelNo;

    /**
     * 展示顺序
     */
    private Integer seq;

    /**
     * 创建人
     */

    private String createBy;

    /**
     * 创建时间
     */

    private LocalDateTime createTime;

    /**
     * 最后更新人
     */

    private String updateBy;

    /**
     * 最后更新时间
     */
    private LocalDateTime updateTime;

    private Set<OrgVO> children = new TreeSet<>();


    @Override
    public int compareTo(OrgVO o) {
        if(this.levelNo!=o.levelNo){
            return this.levelNo-o.levelNo;
        }
        return this.seq -o.getSeq();
    }

    public void addChild(OrgVO orgVO) {
        this.children.add(orgVO);
    }
}
