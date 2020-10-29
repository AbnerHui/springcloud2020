package com.abner.springcloud.controller;

import com.abner.springcloud.entities.CommonResult;
import com.abner.springcloud.entities.Payment;
import com.abner.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody Payment payment) {
        int res = this.paymentService.create(payment);
        log.info("插入结果---"+res);
        if(res > 0 ) {
            return new CommonResult(200,"插入成功,serverPort:"+serverPort,res);
        }else {
            return new CommonResult(444,"插入数据失败");
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentId(@PathVariable("id") Long id) {
        Payment paymentById = this.paymentService.getPaymentById(id);
        if(paymentById != null ) {
            log.info("查询成功--");
            return new CommonResult(200,"查询成功,serverPort:"+serverPort,paymentById);
        }else {
            return new CommonResult(444,"记录为空,id为"+id);
        }
    }

    /**
     * 获取注册的服务信息
     */
    @GetMapping(value = "/payment/discovery")
    public Object discovery() {
        /**
         * 获得eureka上面有几个服务
         */
        List<String> services = discoveryClient.getServices();
        for (String element : services) {
            log.info("*element:"+element);
        }
        /**
         * 根据微服务名称获得该下面的实例
         */
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info("ID:"+instance.getInstanceId()+"--- 主机:"+instance.getHost()+"--- 端口号:"+instance.getPort()+"--- 地址:"+instance.getUri());
        }
        return this.discoveryClient;
    }

    @GetMapping(value = "/payment/lb")
    public String getPaymentLB()
    {
        return serverPort;
    }

    @GetMapping(value = "/payment/feign/timeout")
    public String paymentFeignTimeout() {
        int time = 3 ;
        // 业务逻辑处理正确，但是需要耗费3秒钟
        try { TimeUnit.SECONDS.sleep(time); } catch (InterruptedException e) { e.printStackTrace(); }
        return serverPort;
    }

    @GetMapping("/payment/zipkin")
    public String paymentZipkin() {
        return "hi ,i'am paymentzipkin server fall back，welcome to atguigu，O(∩_∩)O哈哈~";
    }
}
