public class Main {

    public static void oddEvenSort(int[] arr){
        int exchFlag = 1,start = 0;
        while(exchFlag == 1 || start == 1){
            exchFlag = 0;
            for(int i = start ;i< arr.length-1;i++){
                if( arr[i] > arr[i+1]){
                    int tmp = arr[i];
                    arr[i] = arr[i+1];
                    arr[i+1] = tmp;
                    exchFlag  =1;
                }
            }
            if(start == 0){
                start = 1;
            }
            else{
                start = 0;
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {
        int[] arr = { 1,6,4,2,9,3,10};
        ParallSort parallSort = new ParallSort(arr);
        parallSort.pOddEvenSort();
        for(int num : arr){
            System.out.print(num+" ");
        }
    }
}
