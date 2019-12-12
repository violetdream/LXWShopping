package com.personal.shopping.dal.persistence;

import com.personal.shopping.dal.entitys.Panel;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface PanelMapper{

    List<Panel> selectPanelContentById(@Param("panelId")Integer panelId);

    List<Panel> selectPanelContents(Map paramMap);
}