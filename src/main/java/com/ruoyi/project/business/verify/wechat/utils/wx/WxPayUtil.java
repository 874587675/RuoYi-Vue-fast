//package com.ruoyi.project.business.verify.wechat.utils.wxpay;
//
//import cn.hutool.core.io.IoUtil;
//import com.alibaba.fastjson.JSON;
//import com.wechat.pay.java.core.RSAAutoCertificateConfig;
//import com.wechat.pay.java.core.exception.HttpException;
//import com.wechat.pay.java.core.exception.MalformedMessageException;
//import com.wechat.pay.java.core.exception.ServiceException;
//import com.wechat.pay.java.core.exception.ValidationException;
//import com.wechat.pay.java.core.notification.NotificationParser;
//import com.wechat.pay.java.core.notification.RequestParam;
//import com.wechat.pay.java.core.util.GsonUtil;
//import com.wechat.pay.java.service.payments.app.AppServiceExtension;
//import com.wechat.pay.java.service.payments.h5.H5Service;
//import com.wechat.pay.java.service.payments.h5.model.H5Info;
//import com.wechat.pay.java.service.payments.jsapi.*;
//import com.wechat.pay.java.service.payments.jsapi.model.*;
//import com.wechat.pay.java.service.payments.jsapi.model.Amount;
//import com.wechat.pay.java.service.payments.model.Transaction;
//import com.wechat.pay.java.service.payments.nativepay.NativePayService;
//import com.wechat.pay.java.service.refund.RefundService;
//import com.wechat.pay.java.service.refund.model.*;
//import com.yima.baseproj.utils.ResultModel;
//import lombok.extern.slf4j.Slf4j;
//
//import javax.servlet.http.HttpServletRequest;
//
///**
// * https://pay.weixin.qq.com/wiki/doc/apiv3/apis/index.shtml
// * https://github.com/wechatpay-apiv3/wechatpay-java
// * https://hub.fgit.ml/wechatpay-apiv3/wechatpay-java
// */
//@Slf4j
//public class WxPayUtil {
//
//    private static RSAAutoCertificateConfig config =
//            new RSAAutoCertificateConfig.Builder()
//                    .merchantId(WxPayConfig.merchantId)
//                    .privateKeyFromPath(WxPayConfig.privateKeyPath)
//                    .merchantSerialNumber(WxPayConfig.merchantSerialNumber)
//                    .apiV3Key(WxPayConfig.apiV3key)
//                    .build();
//
//    public static void main(String[] args) {
//        //APP下单
////        ResultModel<com.wechat.pay.java.service.payments.app.model.PrepayWithRequestPaymentResponse> prepayByAppResultModel = prepayByApp("一码代码库","20230628002","附加数据",100);
////        if(prepayByAppResultModel.getIsSuccess()){
////            //成功
////        }
//        //H5下单
////        ResultModel<com.wechat.pay.java.service.payments.h5.model.PrepayResponse> prepayByH5ResultModel = prepayByH5("闫伟测试","20230620013","附加数据",100,"60.222.62.135");
////        if(prepayByH5ResultModel.getIsSuccess()){
////            //成功
////        }
//        //Native下单(扫码支付)
////        ResultModel<com.wechat.pay.java.service.payments.nativepay.model.PrepayResponse> prepayByNativepayResultModel = prepayByNative("微信开放平台-网站应用-接口测试","20230630002","附加数据",100);
////        if(prepayByNativepayResultModel.getIsSuccess()){
////            //成功
////        }
//
////        JSAPI下单(小程序支付).预支付交易单
////        ResultModel<PrepayWithRequestPaymentResponse> prepayByJsapiResultModel = WxPayUtil.prepayByJsapi("一码代码库", "20230626001", "附加数据", "oyPCE5HPp3Ct9Uey5lJ2lipTGoBY", 100);
////        if (prepayByJsapiResultModel.getIsSuccess()) {
////            //成功
////        }
////        查询订单-商户订单号查询
////        ResultModel<Transaction> queryByOutTradeNoResultModel = queryByOutTradeNo("20230620012");
////        if (queryByOutTradeNoResultModel.getIsSuccess()) {
////            //成功
////        }
////        查询订单-微信支付订单号查询
////        ResultModel<Transaction> queryByIdResultModel = queryByTransactionId("4200001861202306200024913283");
////        if (queryByIdResultModel.getIsSuccess()) {
////            //成功
////        }
//        //关闭订单
////        ResultModel<Transaction> closeByOutTradeNoResultModel = closeByOutTradeNo("20230620012");
////        if (closeByOutTradeNoResultModel.getIsSuccess()) {
////            //成功
////        }
//        //申请退款-商户订单号
////        ResultModel<Refund> refundsByOutTradeNoResultModel = refundsByOutTradeNo("20230620012","2023062001201",100,10);
////        if (refundsByOutTradeNoResultModel.getIsSuccess()) {
////            //成功
////        }
//        //申请退款-微信支付订单号
////        ResultModel<Refund> refundsByOutTradeNoResultModel = refundsByTransactionId("4200001861202306200024913283","2023062001202",100,10);
////        if (refundsByOutTradeNoResultModel.getIsSuccess()) {
////            //成功
////        }
//        //查询单笔退款
////        ResultModel<Refund> queryByOutRefundNoResultModel = refundsQueryByOutRefundNo("2023062001202");
////        if (queryByOutRefundNoResultModel.getIsSuccess()) {
////            //成功
////        }
//
//    }
//
//
//
//    /**
//     * H5下单
//     * 商户系统先调用该接口在微信支付服务后台生成预支付交易单，返回正确的预支付交易会话标识后再按Native、JSAPI、APP等不同场景生成交易串调起支付。
//     * https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter3_3_1.shtml
//     *
//     * @param description   商品描述
//     * @param outTradeNo    商户订单号
//     * @param attach    附加数据
//     * @param total 总金额
//     * @param payerClientIp 用户终端IP
//     * @return
//     */
//    public static ResultModel<com.wechat.pay.java.service.payments.h5.model.PrepayResponse> prepayByH5(String description, String outTradeNo, String attach, Integer total,String payerClientIp) {
//        log.info("H5下单-开始");
//
//        ResultModel<com.wechat.pay.java.service.payments.h5.model.PrepayResponse> resultModel = new ResultModel<>();
//        // 构建service
//        H5Service service = new H5Service.Builder().config(config).build();
//        //构建请求
//        com.wechat.pay.java.service.payments.h5.model.PrepayRequest request = new com.wechat.pay.java.service.payments.h5.model.PrepayRequest();
//        //基础信息
//        request.setAppid(WxPayConfig.appId);
//        request.setMchid(WxPayConfig.merchantId);
//        request.setDescription(description);//商品描述
//        request.setOutTradeNo(outTradeNo);//商户系统内部订单号
//        request.setAttach(attach);//附加数据
//        request.setNotifyUrl(WxPayConfig.notifyUrl);
//        //订单金额信息
//        com.wechat.pay.java.service.payments.h5.model.Amount amount = new com.wechat.pay.java.service.payments.h5.model.Amount();
//        amount.setTotal(total);
//        request.setAmount(amount);
//        //场景信息
//        com.wechat.pay.java.service.payments.h5.model.SceneInfo sceneInfo = new com.wechat.pay.java.service.payments.h5.model.SceneInfo();
//        sceneInfo.setPayerClientIp(payerClientIp);
//        //H5场景信息
//        H5Info h5Info = new H5Info();
//        h5Info.setType("Wap");
//        sceneInfo.setH5Info(h5Info);
//        request.setSceneInfo(sceneInfo);
//
//        try {
//            com.wechat.pay.java.service.payments.h5.model.PrepayResponse response = service.prepay(request);
//            log.info("H5下单-结果：{}", response.toString());
//            resultModel.setIsSuccess(true);
//            resultModel.setData(response);
//        } catch (Exception ex) {
//            setException(resultModel, ex);
//        }
//        log.info("H5下单-结束：{}",JSON.toJSONString(resultModel));
//        return resultModel;
//    }
//
//    /**
//     * Native下单(扫码支付)
//     * 商户系统按微信支付协议生成支付二维码，用户再用微信“扫一扫”完成支付的模式
//     * https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter3_3_1.shtml
//     *
//     * @param description   商品描述
//     * @param outTradeNo    商户订单号
//     * @param attach    附加数据
//     * @param total 总金额
//     * @return
//     */
//    public static ResultModel<com.wechat.pay.java.service.payments.nativepay.model.PrepayResponse> prepayByNative(String description, String outTradeNo, String attach, Integer total) {
//        log.info("Native下单(扫码支付)-开始");
//
//        ResultModel<com.wechat.pay.java.service.payments.nativepay.model.PrepayResponse> resultModel = new ResultModel<>();
//        // 构建service
//        NativePayService service = new NativePayService.Builder().config(config).build();
//        //构建请求
//        com.wechat.pay.java.service.payments.nativepay.model.PrepayRequest request = new com.wechat.pay.java.service.payments.nativepay.model.PrepayRequest();
//        //基础信息
//        request.setAppid(WxPayConfig.appId);
//        request.setMchid(WxPayConfig.merchantId);
//        request.setDescription(description);//商品描述
//        request.setOutTradeNo(outTradeNo);//商户系统内部订单号
//        request.setAttach(attach);//附加数据
//        request.setNotifyUrl(WxPayConfig.notifyUrl);
//        //订单金额信息
//        com.wechat.pay.java.service.payments.nativepay.model.Amount amount = new com.wechat.pay.java.service.payments.nativepay.model.Amount();
//        amount.setTotal(total);
//        request.setAmount(amount);
//
//        try {
//            com.wechat.pay.java.service.payments.nativepay.model.PrepayResponse response = service.prepay(request);
//            log.info("Native下单(扫码支付)-结果：{}", response.toString());
//            resultModel.setIsSuccess(true);
//            resultModel.setData(response);
//        } catch (Exception ex) {
//            setException(resultModel, ex);
//        }
//        log.info("Native下单(扫码支付)-结束：{}",JSON.toJSONString(resultModel));
//        return resultModel;
//    }
//
//    /**
//     * 查询订单-商户订单号查询
//     * 商户可以通过查询订单接口主动查询订单状态，完成下一步的业务逻辑。查询订单状态可通过微信支付订单号或商户订单号两种方式查询
//     * https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter3_1_2.shtml
//     *
//     * @param outTradeNo 商户系统内部订单号，只能是数字、大小写字母_-*且在同一个商户号下唯一。
//     * @return
//     */
//    public static ResultModel<Transaction> queryByOutTradeNo(String outTradeNo) {
//        log.info("查询订单-商户订单号查询-开始");
//
//        ResultModel<Transaction> resultModel = new ResultModel<>();
//        // 构建service
//        JsapiServiceExtension service = new JsapiServiceExtension.Builder().config(config).build();
//        //构建请求
//        QueryOrderByOutTradeNoRequest request = new QueryOrderByOutTradeNoRequest();
//        request.setMchid(WxPayConfig.merchantId);
//        request.setOutTradeNo(outTradeNo);
//        try {
//            Transaction transaction = service.queryOrderByOutTradeNo(request);
//            resultModel.setIsSuccess(true);
//            resultModel.setData(transaction);
//        } catch (Exception ex) {
//            setException(resultModel, ex);
//        }
//        log.info("查询订单-商户订单号查询-结束：{}",JSON.toJSONString(resultModel));
//        return resultModel;
//    }
//
//    /**
//     * 查询订单-微信支付订单号查询
//     * 商户可以通过查询订单接口主动查询订单状态，完成下一步的业务逻辑。查询订单状态可通过微信支付订单号或商户订单号两种方式查询
//     * https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter3_1_2.shtml
//     *
//     * @param transactionId 微信支付订单号
//     * @return
//     */
//    public static ResultModel<Transaction> queryByTransactionId(String transactionId) {
//        log.info("查询订单-微信支付订单号查询-开始");
//
//        ResultModel<Transaction> resultModel = new ResultModel<>();
//        // 构建service
//        JsapiServiceExtension service = new JsapiServiceExtension.Builder().config(config).build();
//        //构建请求
//        QueryOrderByIdRequest request = new QueryOrderByIdRequest();
//        request.setMchid(WxPayConfig.merchantId);
//        request.setTransactionId(transactionId);
//        try {
//            Transaction transaction = service.queryOrderById(request);
//            resultModel.setIsSuccess(true);
//            resultModel.setData(transaction);
//        } catch (Exception ex) {
//            setException(resultModel, ex);
//        }
//        log.info("查询订单-微信支付订单号查询-结束：{}",JSON.toJSONString(resultModel));
//        return resultModel;
//    }
//
//    /**
//     * 关闭订单
//     * 以下情况需要调用关单接口：
//     * 1、商户订单支付失败需要生成新单号重新发起支付，要对原订单号调用关单，避免重复支付；
//     * 2、系统下单后，用户支付超时，系统退出不再受理，避免用户继续，请调用关单接口。
//     * https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter3_1_3.shtml
//     *
//     * @param outTradeNo 商户系统内部订单号，只能是数字、大小写字母_-*且在同一个商户号下唯一
//     * @return
//     */
//    public static ResultModel<Transaction> closeByOutTradeNo(String outTradeNo) {
//        log.info("关闭订单-开始");
//
//        ResultModel<Transaction> resultModel = new ResultModel<>();
//        // 构建service
//        JsapiServiceExtension service = new JsapiServiceExtension.Builder().config(config).build();
//        //构建请求
//        CloseOrderRequest request = new CloseOrderRequest();
//        request.setMchid(WxPayConfig.merchantId);
//        request.setOutTradeNo(outTradeNo);
//        try {
//            service.closeOrder(request);
//            resultModel.setIsSuccess(true);
//        } catch (Exception ex) {
//            setException(resultModel, ex);
//        }
//        log.info("关闭订单-结束：{}", JSON.toJSONString(resultModel));
//        return resultModel;
//    }
//
//    /**
//     * 申请退款-商户订单号
//     * 当交易发生之后一年内，由于买家或者卖家的原因需要退款时，卖家可以通过退款接口将支付金额退还给买家，微信支付将在收到退款请求并且验证成功之后，将支付款按原路退还至买家账号上。
//     * https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter3_1_9.shtml
//     *
//     * @param outTradeNo  商户订单号
//     * @param outRefundNo 商户退款单号
//     * @param total       原订单金额
//     * @param refund      退款金额
//     * @return
//     */
//    public static ResultModel<Refund> refundsByOutTradeNo(String outTradeNo, String outRefundNo, Integer total, Integer refund) {
//        log.info("申请退款-开始");
//
//        ResultModel<Refund> resultModel = new ResultModel<>();
//        // 构建service
//        RefundService service = new RefundService.Builder().config(config).build();
//
//        //构建请求
//        CreateRequest request = new CreateRequest();
//        request.setOutTradeNo(outTradeNo);
//        request.setOutRefundNo(outRefundNo);
//        request.setNotifyUrl(WxPayConfig.refundNotifyUrl);
//        //金额信息
//        AmountReq amountReq = new AmountReq();
//        amountReq.setRefund(refund + 0l);
//        amountReq.setTotal(total + 0l);
//        amountReq.setCurrency("CNY");
//        request.setAmount(amountReq);
//
//        try {
//            service.create(request);
//            resultModel.setIsSuccess(true);
//        } catch (Exception ex) {
//            setException(resultModel, ex);
//        }
//        log.info("申请退款-结束：{}",JSON.toJSONString(resultModel));
//        return resultModel;
//    }
//
//    /**
//     * 申请退款
//     * 当交易发生之后一年内，由于买家或者卖家的原因需要退款时，卖家可以通过退款接口将支付金额退还给买家，微信支付将在收到退款请求并且验证成功之后，将支付款按原路退还至买家账号上。
//     * https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter3_1_9.shtml
//     *
//     * @param transactionId 微信支付订单号
//     * @param outRefundNo   商户退款单号
//     * @param total         原订单金额
//     * @param refund        退款金额
//     * @return
//     */
//    public static ResultModel<Refund> refundsByTransactionId(String transactionId, String outRefundNo, Integer total, Integer refund) {
//        log.info("申请退款-开始");
//
//        ResultModel<Refund> resultModel = new ResultModel<>();
//        // 构建service
//        RefundService service = new RefundService.Builder().config(config).build();
//
//        //构建请求
//        CreateRequest request = new CreateRequest();
//        request.setTransactionId(transactionId);
//        request.setOutRefundNo(outRefundNo);
//        request.setNotifyUrl(WxPayConfig.refundNotifyUrl);
//        //金额信息
//        AmountReq amountReq = new AmountReq();
//        amountReq.setRefund(refund + 0l);
//        amountReq.setTotal(total + 0l);
//        amountReq.setCurrency("CNY");
//        request.setAmount(amountReq);
//
//        try {
//            service.create(request);
//            resultModel.setIsSuccess(true);
//        } catch (Exception ex) {
//            setException(resultModel, ex);
//        }
//        log.info("申请退款-结束：{}",JSON.toJSONString(resultModel));
//        return resultModel;
//    }
//
//    /**
//     * 查询单笔退款
//     * 提交退款申请后，通过调用该接口查询退款状态。退款有一定延时，建议在提交退款申请后1分钟发起查询退款状态，一般来说零钱支付的退款5分钟内到账，银行卡支付的退款1-3个工作日到账。
//     * https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter3_5_10.shtml
//     *
//     * @param outRefundNo 商户退款单号
//     * @return
//     */
//    public static ResultModel<Refund> refundsQueryByOutRefundNo(String outRefundNo) {
//        log.info("查询单笔退款-开始");
//
//        ResultModel<Refund> resultModel = new ResultModel<>();
//        // 构建service
//        RefundService service = new RefundService.Builder().config(config).build();
//        //构建请求
//        QueryByOutRefundNoRequest request = new QueryByOutRefundNoRequest();
//        request.setOutRefundNo(outRefundNo);
//        try {
//            Refund transaction = service.queryByOutRefundNo(request);
//            resultModel.setIsSuccess(true);
//            resultModel.setData(transaction);
//        } catch (Exception ex) {
//            setException(resultModel, ex);
//        }
//        log.info("查询单笔退款-结束：{}",JSON.toJSONString(resultModel));
//        return resultModel;
//    }
//
//    /**
//     * 支付通知
//     * 微信支付通过支付通知接口将用户支付成功消息通知给商户
//     * https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter3_5_10.shtml
//     *
//     * @return
//     */
//    public static ResultModel<Transaction> notify(HttpServletRequest request) {
//        log.info("支付通知-开始");
//        ResultModel<Transaction> resultModel = new ResultModel<>();
//
//        try {
//            String body = IoUtil.getUtf8Reader(request.getInputStream()).readLine();
//            RequestParam requestParam = new RequestParam.Builder()
//                    .serialNumber(request.getHeader("Wechatpay-Serial"))
//                    .nonce(request.getHeader("Wechatpay-Nonce"))
//                    .signature(request.getHeader("Wechatpay-Signature"))
//                    .timestamp(request.getHeader("Wechatpay-Timestamp"))
//                    .signType(request.getHeader("Wechatpay-Signature-Type"))
//                    .body(body)
//                    .build();
//            
//            NotificationParser parser = new NotificationParser(config);
//            // 以支付通知回调为例，验签、解密并转换成 Transaction
//            Transaction transaction = parser.parse(requestParam, Transaction.class);
//            resultModel.setIsSuccess(true);
//            resultModel.setData(transaction);
//        } catch (Exception ex) {
//            setException(resultModel, ex);
//        }
//        log.info("支付通知-结束：{}", GsonUtil.getGson().toJson(resultModel));
//        return resultModel;
//    }
//
//    /**
//     * 退款结果通知
//     * 退款状态改变后，微信会把相关退款结果发送给商户。
//     * https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter3_5_11.shtml
//     *
//     * @return
//     */
//    public static ResultModel<RefundNotification> refundNotify(HttpServletRequest request) {
//        log.info("退款结果通知-开始");
//        ResultModel<RefundNotification> resultModel = new ResultModel<>();
//
//        try {
//            String body = IoUtil.getUtf8Reader(request.getInputStream()).readLine();
//            RequestParam requestParam = new RequestParam.Builder()
//                    .serialNumber(request.getHeader("Wechatpay-Serial"))
//                    .nonce(request.getHeader("Wechatpay-Nonce"))
//                    .signature(request.getHeader("Wechatpay-Signature"))
//                    .timestamp(request.getHeader("Wechatpay-Timestamp"))
//                    .signType(request.getHeader("Wechatpay-Signature-Type"))
//                    .body(body)
//                    .build();
//            NotificationParser parser = new NotificationParser(config);
//            // 以支付通知回调为例，验签、解密并转换成 Transaction
//            RefundNotification refundNotification = parser.parse(requestParam, RefundNotification.class);
//            resultModel.setIsSuccess(true);
//            resultModel.setData(refundNotification);
//        } catch (Exception ex) {
//            setException(resultModel, ex);
//        }
//        log.info("退款结果通知-结束：{}", GsonUtil.getGson().toJson(resultModel));
//        return resultModel;
//    }
//
//    /**
//     * 设置异常
//     *
//     * @param resultModel
//     * @param ex
//     */
//    private static void setException(ResultModel resultModel, Exception ex) {
//        if (ex instanceof HttpException) {
//            //调用微信支付服务，当发生 HTTP 请求异常时抛出该异常。
//            resultModel.setIsSuccess(false);
//            resultModel.setMessage(ex.getMessage());
//        } else if (ex instanceof ValidationException) {
//            //当验证微信支付签名失败时抛出该异常。
//            resultModel.setIsSuccess(false);
//            resultModel.setMessage(ex.getMessage());
//        } else if (ex instanceof ServiceException) {
//            //调用微信支付服务，发送 HTTP 请求成功，HTTP 状态码小于200或大于等于300。
//            ServiceException serviceException = (ServiceException) ex;
//            resultModel.setIsSuccess(false);
//            resultModel.setMessage(serviceException.getErrorMessage());
//            resultModel.setCode(serviceException.getErrorCode());
//        } else if (ex instanceof MalformedMessageException) {
//            //服务返回成功，返回内容异常
//            resultModel.setIsSuccess(false);
//            resultModel.setMessage(ex.getMessage());
//        } else {
//            resultModel.setIsSuccess(false);
//            resultModel.setMessage(ex.getMessage());
//        }
//    }
//}
