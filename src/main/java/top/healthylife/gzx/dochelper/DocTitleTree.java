package top.healthylife.gzx.dochelper;

import cn.hutool.core.util.ObjectUtil;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 功能描述: WordTitle
 *
 * @author maoxiaomeng
 * @date 20230609
 */
@Data
@Accessors(chain = true)
public class DocTitleTree {
    String titleName;
    int index;
    String parentNo;
    String parentName;
    String fontFamily;
    Integer level;
    boolean hasContent;
    List<DocContent> docContent;
    List<DocTitleTree> children;

    public boolean hasChildren() {
        return this.children != null && this.children.size() > 0;
    }

    public String getNo() {
        if (ObjectUtil.isEmpty(parentNo)) {
            return index + "";
        }
        return parentNo + "." + index;
    }

    public String printName() {
        if (ObjectUtil.isEmpty(parentName)) {
            return this.getTitleName();
        }
        return parentName + "\t" + titleName;
    }

    public String getTitle() {
        return getNo() + " " + titleName;
    }


}
