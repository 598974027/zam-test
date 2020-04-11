package jcommander;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

/**
 * 功能描述:
 *
 * @author zhangam
 * @time 2019/4/8 18:33
 * @see
 **/
@Parameters(separators = "=", commandDescription = "java cli")
public class ClientOptions {

    @Parameter(names = {"--level", "-l"}, description = "", required = true)
    public Integer level;

    @Parameter(names = {"--help", "-h"}, description = "", required = true)
    public String help;

}
