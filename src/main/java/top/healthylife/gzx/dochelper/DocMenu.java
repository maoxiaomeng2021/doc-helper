package top.healthylife.gzx.dochelper;

import lombok.Data;

import java.util.List;

/**
 * @author maoxiaomeng
 */
@Data
public class DocMenu {
    String name;
    List<DocMenu> children;
    boolean noLeaf;
    List<DocContent> docContents;

    /**
     * 如果是叶子节点,则不会追加固定菜单
     * @return 是否是叶子节点
     */
    public boolean hasLeaf(){
        return !noLeaf;
    }

    /**
     * 如果目录声明
     * 如果声明了是叶子节点,则不会追加固定菜单
     * @return
     */
    public boolean hasNoLeaf(){
        return this.noLeaf;
    }
}
