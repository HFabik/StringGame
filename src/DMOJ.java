import java.io.*;
import java.util.*;

public class DMOJ
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        boolean y = true;
        for (int i = 0; i< n; i ++)
        {
            String s = in.nextLine().toLowerCase();
            for (int j = 1; j < s.length(); j ++)
            {
                if ()
            }
        }
        /*StringBuilder noSp = new StringBuilder();
        for (int i = 0; i < s.length(); i++)
        {
            if (s.charAt(i) != ' ' )
                noSp.append(s.charAt(i));
        }
        int l = 0, cl = 0, il = -1;
        for (int i = 0; i < noSp.length(); i ++)
        {
            if (noSp.charAt(i) == 'L')
            {
                l++;
                if ((i + 1 == noSp.length() || noSp.charAt(i+1) != 'L')&& noSp.charAt(i-1) == 'L')
                {
                    for (int j = i; j > il; j--)
                    {
                        if (noSp.charAt(j) == 'L')
                            cl++;
                    }
                    il = i;
                }
            }
        }
        System.out.println(l + " " + cl);*/
    }
}
