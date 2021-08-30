import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 功能描述: Test
 *
 * @author zhangaomin
 * @time 2021/5/7 16:15
 **/
public class Test {

    public static int maxLength(int[] arr) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        int max = 1;
        for (int i = 0, j = 0; j < arr.length; j++) {
            // 如果该元素重复,调整起始位置
            if (hashMap.containsKey(arr[j])) {
                i = Math.max(hashMap.get(arr[j]) + 1, i);
                // 因为当前元素已经重复了，所以新的i要在当前元素上加1，也就是向后挪一位
                // 为了避免遇到的索引比当前i还小，所以要比较大小
                // 为什么？因为太小的索引不需要再去计算，之前的结果已经存过max中了
            }
            // 如果改元素没有重复
            hashMap.put(arr[j], j);
            max = Math.max(max, j - i + 1);
        }
        return max;
    }

    public static int search(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        int mid = 0;
        while (low <= high) {
            mid = (high + low) / 2;
            if (nums[mid] == target) {
                while (mid != 0 && (nums[mid - 1] == nums[mid])) {
                    mid--;
                }
                return mid;
            } else if (nums[mid] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    public static int[] search2(int[] nums) {
        int n;
        for (int i = 1; i < nums.length; i++) {
            n = nums[i];
            int j = i - 1;
            while (j >= 0 && nums[j] < n) {
                nums[j + 1] = nums[j];
                j--;
            }
            nums[j + 1] = n;
        }

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] > nums[i]) {
                    int temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                }
            }
        }

        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length - i - 1; j++) {
                if (nums[j] > nums[j + 1]) {
                    int temp = nums[j + 1];
                    nums[j + 1] = nums[j];
                    nums[j] = temp;
                }
            }
        }
        return nums;
    }

    public static void preorder(TreeNode root, List<Integer> list) {
        if (root != null) {
            list.add(root.val);
            preorder(root.left, list);
            preorder(root.right, list);
        }
    }

    public static void inorder(TreeNode root, List<Integer> list) {
        if (root != null) {
            inorder(root.left, list);
            list.add(root.val);
            inorder(root.right, list);
        }
    }

    public static void postorder(TreeNode root, List<Integer> list) {
        if (root != null) {
            postorder(root.left, list);
            postorder(root.right, list);
            list.add(root.val);
        }
    }

    public static TreeNode createTreeNode(int[] nums) {
        Test tt = new Test();
        int n = 0;
        TreeNode t = null;
        return t;
    }

    class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        TreeNode(int a) {
            this.val = a;
        }
    }

    public static void main(String[] args) throws Exception {
        int[] nums = {1, 2, 2, 2, 3, 4, 5, 6, 6, 7};
        maxLength(nums);

        Test tt = new Test();
        TreeNode t = tt.new TreeNode(5);
        TreeNode t1 = tt.new TreeNode(2);
        TreeNode t2 = tt.new TreeNode(3);
        TreeNode t3 = tt.new TreeNode(4);
        t2.left = t1;
        t2.right = t3;
        TreeNode t4 = tt.new TreeNode(6);
        t.left = t2;
        t.right = t4;
        List<Integer> list = new ArrayList<>();
        preorder(t, list);

        System.out.println();
    }
}
