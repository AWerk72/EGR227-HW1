import java.util.LinkedList;
import java.util.Queue;
import java.util.*;

/**
 * Austin Werk
 *
 * EGR227- Hw1
 */
public class HtmlValidator {

    //created a global public queue variable
    public Queue<HtmlTag> hTag;

    //constructors
    public HtmlValidator(){
        hTag = new LinkedList<>();
    }

    public HtmlValidator(Queue<HtmlTag> tags){
        if(hTag == null){
            throw new IllegalArgumentException("tag error");
        }
        this.hTag = new LinkedList<>(hTag);
    }

    //addTag method for the global queue
    public void addTag(HtmlTag tag) throws IllegalArgumentException{
        if(tag == null){
            throw new IllegalArgumentException("tag can't be null");
        }
        hTag.add(tag);
    }

    // method to remove elements from the global queue
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

    //validator method to analyze whether or not tags have matching pairs
    public void validate(){
        Stack<HtmlTag> cTag = new Stack<>();
        for(int i=0; i<hTag.size(); i++){
            HtmlTag fTag = hTag.remove();
            hTag.add(fTag);

            if(fTag.isSelfClosing()){
                indent((HtmlTag) hTag, cTag.size());
            } else if(((HtmlTag) hTag).isOpenTag()){
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

    // Method to produce indent found between tags
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
