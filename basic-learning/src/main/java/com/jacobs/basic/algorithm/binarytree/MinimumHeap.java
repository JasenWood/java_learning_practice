package com.jacobs.basic.algorithm.binarytree;

/**
 * 参考文章 https://www.cnblogs.com/linjj/p/5260763.html
 *
 * 塞到一个完全二叉树里面去调整，这样就可以用一个数组来进行存储 注意完全二叉树有一个性质，最后一个非叶子节点是第n/2个节点，从1数起而不是从0数起
 *
 *
 * 把n个元素建立一个堆，首先我们可以将这n个节点以自顶向下，从左到右的方式从1到n编码，
 *
 * 可以将这n个节点转换成一棵完全二叉树。紧接着从最后一个非叶节点（节点编号为n/2）开始到根节点（节点编号为1）
 *
 * ，逐个扫描所有的节点，根据需要将当前节点向下调整，直到以当前结点为根节点的子树符合堆的特性。时间复杂度是O(N)
 *
 * @author lichao
 * @date 2018/05/04
 */
public class MinimumHeap {

    public static void main(String[] args) {
        MinimumHeap minimumHeap = new MinimumHeap();
        minimumHeap.heapArr = new int[]{99, 5, 36, 7, 22, 17, 46, 12, 2, 19, 25, 28, 1, 92};
        minimumHeap.heapSize = minimumHeap.heapArr.length;

        minimumHeap.create();

        int len = minimumHeap.heapSize;
        //删除顶部元素，连续删除n次，其实也就是从大到小把数输出来
        for (int i = 1; i <= len; i++) {
            System.out.print(" " + minimumHeap.deleteMax());
        }
    }

    private int heapSize;
    private int[] heapArr;

    //传入一个需要向下调整的结点编号i，这里传入1，即从堆的顶点开始向下调整
    public void shiftDown(int i) {
        int t, flag = 0;//flag用来标记是否需要继续向下调整
        //当i结点有儿子的时候（其实是至少有左儿子的情况下）并且有需要继续调整的时候循环窒执行
        while (i * 2 < heapSize && flag == 0) {
            //首先判断他和他左儿子的关系，并用t记录值较小的结点编号
            if (heapArr[i] > heapArr[i * 2]) {
                t = i * 2;
            } else {
                t = i;
            }
            //如果他有右儿子的情况下，再对右儿子进行讨论
            if (i * 2 + 1 < heapSize) {
                //如果右儿子的值更小，更新较小的结点编号
                if (heapArr[t] > heapArr[i * 2 + 1]) {
                    t = i * 2 + 1;
                }
            }
            //如果发现最小的结点编号不是自己，说明子结点中有比父结点更小的
            if (t != i) {
                swap(t, i);//交换它们，注意swap函数需要自己来写
                i = t;//更新i为刚才与它交换的儿子结点的编号，便于接下来继续向下调整
            } else {
                flag = 1;//则否说明当前的父结点已经比两个子结点都要小了，不需要在进行调整了
            }
        }
    }

    //插入节点的时候:传入一个需要向上调整的结点编号i
    public void shiftUp(int i) {
        int flag = 0; //用来标记是否需要继续向上调整
        if (i == 1) {
            return; //如果是堆顶，就返回，不需要调整了
        }
        //不在堆顶 并且 当前结点i的值比父结点小的时候继续向上调整
        while (i != 1 && flag == 0) {
            //判断是否比父结点的小
            if (heapArr[i] < heapArr[i / 2]) {
                swap(i, i / 2);//交换他和他爸爸的位置
            } else {
                flag = 1;//表示已经不需要调整了，当前结点的值比父结点的值要大
            }
            i = i / 2; //这句话很重要，更新编号i为它父结点的编号，从而便于下一次继续向上调整
        }
    }


    //删除最大的元素
    int deleteMax() {
        int t;
        t = heapArr[1];//用一个临时变量记录堆顶点的值
        heapArr[1] = heapArr[heapSize - 1];//将堆得最后一个点赋值到堆顶
        heapSize--;//堆的元素减少1
        shiftDown(1);//向下调整
        return t;//返回之前记录的堆得顶点的最大值
    }

    //建立最小堆
    public void create() {
        int i;
        //从最后一个非叶结点到第1个结点依次进行向上调整
        for (i = heapSize / 2; i >= 1; i--) {
            shiftDown(i);
        }
    }

    public void swap(int x, int y) {
        int t;
        t = heapArr[x];
        heapArr[x] = heapArr[y];
        heapArr[y] = t;
    }
}
