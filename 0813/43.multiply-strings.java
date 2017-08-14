// 1
public class Solution {
    public String multiply(String num1, String num2) {
        if (num1.length() == 0 || num2.length() == 0) {
        	return "0";
        }
        //reverse the two numbers.
        int[] input1 = parseInput(num1);
        int[] input2 = parseInput(num2);
        int[] output = computeOutput(input1,input2);
        return generateOutput(output);
    }

    private int[] parseInput(String input) {
    	int[] ret = new int[input.length()];
    	for(int i = 0; i < ret.length; i++) {
    		ret[i] = input.charAt(input.length() -1 -i) - '0';
    		// System.out.println(ret[i]);
    	}
    	return ret;
    }

    private int[] computeOutput(int[] input1, int[] input2) {
    	int[] ret = new int[input1.length + input2.length];
    	for (int i  = 0; i < input1.length; i++) {
    		for(int j = 0; j < input2.length; j++) {
    			ret[i+j] += input1[i]*input2[j];
    		}
    	}
    	return ret;
    }

    private String generateOutput(int[] numbers) {
    	StringBuilder sb = new StringBuilder();

    	for (int i = 0; i < numbers.length; i++) {
    		int carry = numbers[i] /10;
    		int digit = numbers[i] % 10;
    		sb.insert(0,digit);
    		if (i < numbers.length -1) {
    			numbers[i+1] += carry;
    		}
    	}
    	while(sb.length() > 0 && sb.charAt(0) == '0') {
    		sb.deleteCharAt(0);
    	}
    	return (sb.length() == 0 ? "0" : sb.toString());
    }

}









// 2
public String multiply(String num1, String num2) {
        if (num1 == null || num2 == null) return null;
        int[] result = new int[num1.length() + num2.length()];
        for (int i = 0; i < num1.length(); i++) {
            for (int j = 0; j < num2.length(); j++) {
                int val1 = num1.charAt(num1.length() - 1 - i) - '0';
                int val2 = num2.charAt(num2.length() - 1 - j) - '0';
                result[i + j] += val1 * val2;
            }
        }
        int carry = 0;
        for (int i = 0; i < result.length; i++) {
            int sum = result[i] + carry;
            result[i] = sum % 10;
            carry = sum / 10;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < result.length; i++){
            sb.insert(0, result[i]);
        }
        while (sb.charAt(0) == '0' && sb.length() > 1){
            sb.deleteCharAt(0);
        }
        return sb.toString();

    }
