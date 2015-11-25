package io.tbud.boot.dubbo.filter;

import com.alibaba.dubbo.rpc.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class ProviderInterfaceLogFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger("interfacelog");

    public Result invoke(Invoker<?> invoker, Invocation inv) throws RpcException {
        long startTimeMills = System.currentTimeMillis();
        InterfaceLog interfaceLog = new InterfaceLog();

        Throwable exception = null;

        Result result = null;
        try {
            RpcContext context = RpcContext.getContext();
            Map requestParams = invoker.getUrl().getParameters();

            interfaceLog.setMethodName(invoker.getInterface().getName() + "." + inv.getMethodName());
            String senderName = context.getAttachment("SENDER_APP_NAME");
            interfaceLog.setSenderName(senderName);
            interfaceLog.setReceiverName((String) requestParams.get("application"));
            interfaceLog.setSenderHost(context.getRemoteHost() + ":" + context.getRemotePort());
            interfaceLog.setReceiverHost(context.getLocalHost() + ":" + context.getLocalPort());
            interfaceLog.setSrvGroup((String) requestParams.get("group"));
            interfaceLog.setVersion((String) requestParams.get("version"));

            Class[] types = inv.getParameterTypes();
            if ((types != null) && (types.length > 0)) {
                String[] paramTypes = new String[types.length];
                int i = 0;
                for (Class type : types) {
                    paramTypes[i] = type.getName();
                    i++;
                }
                interfaceLog.setParamTypes(paramTypes);
            }

            interfaceLog.setParamValues(inv.getArguments());

            result = invoker.invoke(inv);

            if (result != null) {
                exception = result.getException();
                interfaceLog.setResultValue(result.getValue());
            }
        } catch (Exception e) {
            exception = e;
            throw e;
        } finally {
            interfaceLog.setCostTime(System.currentTimeMillis() - startTimeMills);
            if (exception != null) {
                interfaceLog.setExceptionMsg(exception.getMessage());
            }
            logger.info(interfaceLog.toString());
        }

        return result;
    }
}
