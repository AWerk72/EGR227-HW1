import java.util.LinkedList;
import java.util.Queue;
import java.util.*;

/**
 * Add your own comments
 */
public class HtmlValidator {

    public Queue<HtmlTag> hTag;

    public HtmlValidator(){
        hTag = new LinkedList<>();
    }

    public HtmlValidator(Queue<HtmlTag> tags){
        if(hTag == null){
            throw new IllegalArgumentException("tag error");
        }
        this.hTag = new LinkedList<>(hTag);
    }

    public void addTag(HtmlTag tag) throws IllegalArgumentException{
        if(tag == null){
            throw new IllegalArgumentException("tag can't be null");
        }
        hTag.add(tag);
    }

    public Queue<HtmlTag> getTags(){
        return new LinkedList<>(hTag);
    }

    public void removeAll(String element) throws IllegalArgumentException{
        if(element == null){
            throw new IllegalArgumentException();
        }
        Queue<HtmlTag> fTag = new LinkedList<>();
        for(HtmlTag tag : hTag) {
            if (!tag.getElement().equalsIgnoreCase(element)){
                fTag.add(tag);
            }
        }
        hTag = fTag;
    }

    public void validate(){
        Stack<HtmlTag> cTag = new Stack<>();
        for(int i=0; i<hTag.size(); i++){
            HtmlTag fTag = hTag.remove();
            hTag.add(fTag);

            if(fTag.isSelfClosing()){
                indent((HtmlTag) hTag, cTag.size());
            } else if(hTag.isEmpty()){ // check method
                indent((HtmlTag) hTag, cTag.size());
                cTag.push((HtmlTag) hTag);
            } else if(!cTag.isEmpty() && ((HtmlTag) hTag).matches(cTag.peek())){
                cTag.pop();
                indent((HtmlTag) hTag, cTag.size());
            } else{
                System.out.println("Error");
            }
        }

        while(!cTag.isEmpty()){
            HtmlTag tag = cTag.pop();
            System.out.println("Error");
        }
    }


private static void indent(HtmlTag tag, int space) {
    StringBuilder sB = new StringBuilder();
    String emptySpace = "    ";
    for (int i = 0; i < space; i++) {
        sB.append(emptySpace);
    }
    sB.append(tag.toString());

    System.out.println(sB);
}

}
