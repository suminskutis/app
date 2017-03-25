package com.company;

/**
 * Created by suminskutis on 2017-03-25.
 */
public class Page {

    private boolean allocated;

    int pageIndex;

    public Page(int pageIndex){
        this.pageIndex = pageIndex;
    }

    public void allocate(){
        allocated = true;
    }
    public void deAllocate(){
        allocated = false;
    }

    public boolean isAllocated() {
        return allocated;
    }
    public int getPageIndex(){
        return pageIndex;
    }

}
