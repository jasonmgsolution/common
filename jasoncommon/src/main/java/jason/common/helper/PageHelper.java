package jason.common.helper;

/**
 * Created by jasonmg_0302 on 2016-03-16.
 */
public class PageHelper {

    private int limit;
    private int start;
    private int total;

    public PageHelper() {
        this(10);
    }

    public PageHelper(int limit) {
        this.limit = limit;
        this.start = -limit;
    }

    public int nextStart(){
        return start += limit;
    }

    public int prevStart(){
        return start -= limit;
    }

    public int setStart(int start){
        return this.start = start;
    }


    public void initStart(){
        this.start = -this.limit;
    }

    public int getStart(){
        return this.start;
    }



    public void setTotal(int total){
        this.total = total;
    }

    public int getTotal(){
        return this.total;
    }

    public void setLimit(int limit){
        this.limit = limit;
    }

    public int getLimit(){
        return this.limit;
    }

    public boolean isFirst(){
        return this.start == -this.limit;
    }

    public boolean hasNext(){
        if(this.start + this.limit < this.total){
            return true;
        }
        return false;
    }


    @Override
    public String toString() {
        return "start = " + start + ", limit = " + limit + ", total = " + total + ", hasNext = " + hasNext();
    }
}
