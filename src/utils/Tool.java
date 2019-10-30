package utils;

/**
 * @author GOLD
 */
public class Tool {

    public static double getWidth(String msg) {
        double width = 20;
        String[] str = msg.split("\n");
        for (int i = 0; i < str.length; i++) {
            double row = 0;
            for (int j = 0; j < str[i].length(); j++) {
                if (isChinese(str[i].charAt(j))) {
                    row += 17;
                } else {
                    row += 9;
                }
            }
            if (row > width) {
                width = row;
            }
            if (width >= 480) {
                width=480;
                break;
            }
        }
        //29中 17=15px/64英 8=7px
        //System.out.println("\nWidth:"+width);
        return width+20;
    }

    public static double getHeight(String msg) {
        double height = 30;
        String[] str = msg.split("\n");
        for (int i = 0; i < str.length; i++) {
            double width = 20;
            for (int j = 0; j < str[i].length(); j++) {
                if (isChinese(str[i].charAt(j))) {
                    width += 17;
                } else {
                    width += 9;
                }
                if (width > 480) {
                    height += 16;
                    width = 20;
                }
            }
            height += 16;
        }
        //System.out.println("Height:"+height);
        return height;
    }

    private static final boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
            return true;
        }
        return false;
    }

}
