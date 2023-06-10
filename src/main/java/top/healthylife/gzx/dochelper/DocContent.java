package top.healthylife.gzx.dochelper;

import lombok.Data;

/**
 * 功能描述: WordDataContent
 *
 * @author maoxiaomeng
 * @date 20230609
 */
@Data
public class DocContent {
    DocContentType dataType;
    Object content;


    /**
     * 声明空白段落
     * @return  空白段落
     */
    public static DocContent buildBlankParagraph(){
        DocContent docContent = new DocContent();
        docContent.setDataType(DocContentType.TEXT);
        docContent.setContent("");
        return docContent;
    }

    /**
     * 声明空白段落
     * @return  空白段落
     */
    public static DocContent buildDocContent(DocContentType dataType,String content){
        DocContent docContent = new DocContent();
        docContent.setDataType(dataType);
        docContent.setContent(content);
        return docContent;
    }
}
