

class SegmentTree {

    int[] tree;


    void buildTree(int[] data){
        this tree = new int[data.length+1];
        recBuild(1,0,data.length-1,data);
    }


    private void recBuild(node, start, stop, data){
        if(start == stop){ //terminal step
            tree[node] = data[start];
            return;
        }

        int mid = (start + stop)/2;
        recBuild(2*node, start,mid);
        recBuild(2*node+1, mid+1,stop);

        tree[node] = tree[2*node] + tree[2*node+1];
    }

    void update(int indx, int val){

    }

    int query(int start, int end){

    }
}
