package fast.cloud.nacos.fastcommongrpcexample.io.grpc;

import io.grpc.Attributes;
import io.grpc.EquivalentAddressGroup;
import io.grpc.NameResolver;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.List;

public class LocalNameResolver extends NameResolver {
//    private final ConfigInterface configInterface;

//    @Inject
//    public LocalNameResolver(ConfigInterface configInterface) {
//        this.configInterface = configInterface;
//    }

    @Override
    public String getServiceAuthority() {
        return "none";
    }

    // 配置可用服务，RPC在调用的时候，轮询选择这里配置的可用的服务地址列表
    @Override
    public void start(Listener listener) {
        ArrayList<EquivalentAddressGroup> addressGroups = new ArrayList<>();
        // 获取rpc地址的配置列表
        // 地址格式 host1:8080,host2:8081
//        Map<String, Object> config = (Map<String, Object>) this.configInterface.getRpcConfig().get("grpc");
//        String[] hosts = config.get("hosts").toString().split(",");
//        for (String host : hosts) {
//            if (host.trim().length() > 0) {
//                String[] address = host.split(":");
        List<SocketAddress> socketAddresses = new ArrayList<>();
        socketAddresses.add(new InetSocketAddress("localhost", 50051));
        List<SocketAddress> socketAddresses2 = new ArrayList<>();
        socketAddresses2.add(new InetSocketAddress("localhost", 50052));
        addressGroups.add(new EquivalentAddressGroup(socketAddresses));
        addressGroups.add(new EquivalentAddressGroup(socketAddresses2));
//            }
//        }
        listener.onAddresses(addressGroups, Attributes.EMPTY);
    }

    @Override
    public void shutdown() {

    }
}

