package wework.constants;

/**
 * 应用内部自定义异常错误信息
 *
 * @author jervis
 * @date 2020/8/6.
 */
public interface ResultErrorConst {

    /**
     * 前端undefined
     */
    String UNDEFINED = "undefined";

    public static enum Error {
        /** 通用错误 */
        GENERAL(1, "系统异常"),
        /** 自定义错误 */
        CUSTOM(2, "");

        private final Integer code;

        private final String desc;

        private Error(int code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public int code() {
            return code;
        }

        public String desc() {
            return desc;
        }
    }

    /**
     * 结果
     */
    enum Result {
        /**
         * 否、错误
         */
        NO(0),
        /**
         * 是、正确
         */
        YES(1);

        private final Integer code;

        Result(Integer code) {
            this.code = code;
        }

        public Integer getCode() {
            return code;
        }
    }

}
