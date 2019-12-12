package com.personal.shopping.dal.persistence;

import com.personal.shopping.dal.entitys.PanelContent;
import java.util.List;
import java.util.Map;
import com.personal.shopping.dal.entitys.PanelContentItem;
import org.apache.ibatis.annotations.Param;

public interface PanelContentMapper{

    List<PanelContentItem> selectPanelContentAndProductWithPanelId(@Param("panelId")Integer panelId);
    List<PanelContent> selectPanelContents(Map map);
}