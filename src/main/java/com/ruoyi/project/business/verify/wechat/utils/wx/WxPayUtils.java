//package com.ruoyi.project.business.verify.wechat.utils.wx;
//
//import com.alibaba.fastjson.JSONObject;
//import com.github.binarywang.wxpay.bean.notify.SignatureHeader;
//import com.github.binarywang.wxpay.bean.notify.WxPayNotifyV3Result;
//import com.github.binarywang.wxpay.bean.request.*;
//import com.github.binarywang.wxpay.bean.result.WxPayOrderQueryV3Result;
//import com.github.binarywang.wxpay.bean.result.enums.TradeTypeEnum;
//import com.github.binarywang.wxpay.config.WxPayConfig;
//import com.github.binarywang.wxpay.constant.WxPayConstants;
//import com.github.binarywang.wxpay.exception.WxPayException;
//import com.github.binarywang.wxpay.service.WxPayService;
//import com.github.binarywang.wxpay.service.impl.WxPayServiceImpl;
//import com.github.binarywang.wxpay.util.RequestUtils;
//
//import com.ruoyi.common.exception.ServiceException;
//import lombok.extern.slf4j.Slf4j;
//
//import javax.servlet.http.HttpServletRequest;
//import java.math.BigDecimal;
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//
//@Slf4j
//public class WxPayUtils {
//
//    private static WxPayService wxPayService() {
//        WxPayConfig payConfig = new WxPayConfig();
//        payConfig.setAppId(WxConstant.PAY_APPID); //应用id
//        payConfig.setMchId(WxConstant.MCH_ID);
//        payConfig.setApiV3Key(WxConstant.PAY_SECRET); //商户APIV3密钥
//        payConfig.setKeyPath(WxConstant.KEY_PATH); //p12
//        payConfig.setPrivateKeyPath(WxConstant.PRIVATE_KEY_PATH); //私钥
//        payConfig.setPrivateCertPath(WxConstant.PRIVATE_CERT_PATH); //证书
//        WxPayService wxPayService = new WxPayServiceImpl();
//        wxPayService.setConfig(payConfig);
//        return wxPayService;
//    }
//
//    /**
//     * 微信小程序支付(v3)
//     *
//     * @param orderNo     订单唯一编号
//     * @param total       金额(元)
//     * @param description 商品描述
//     * @return 返回支付链接
//     */
//    public static String createOrderV3(String orderNo, BigDecimal total, String description) {
//        WxPayUnifiedOrderV3Request request = new WxPayUnifiedOrderV3Request();
//        request.setAmount(new WxPayUnifiedOrderV3Request.Amount()
//                .setCurrency((WxPayConstants.CurrencyType.CNY))
//                .setTotal(BaseWxPayRequest.yuan2Fen(total)));
//        request.setDescription(description);
//        request.setOutTradeNo(orderNo);
//        //异步回调通知
//        request.setNotifyUrl(WxConstant.NOTIFY_URL);
//        request.setTimeExpire(timeExpire());
//        try {
//            return JSONObject.toJSONString(wxPayService().createOrderV3(TradeTypeEnum.APP, request));
//        } catch (Exception e) {
//            log.info("微信小程序支付(v3)异常: " + e);
//        }
//        return null;
//    }
//
//
//
//    /**
//     * 微信小程序退款(v3)
//     *
//     * @param orderNo     原订单编号
//     * @param refundNo    退款唯一编号
//     * @param total       金额(元)
//     * @param refundPrice 退款金额(元)
//     * @return 退款状态
//     */
//    public static boolean refundOrderV3(String orderNo, String refundNo, BigDecimal total, BigDecimal refundPrice) {
//        WxPayRefundV3Request request = new WxPayRefundV3Request();
//        request.setOutTradeNo(orderNo);
//        request.setOutRefundNo(refundNo);
//        request.setAmount(new WxPayRefundV3Request.Amount()
//                .setTotal(BaseWxPayRequest.yuan2Fen(total))
//                .setRefund(BaseWxPayRequest.yuan2Fen(refundPrice))
//                .setCurrency(WxPayConstants.CurrencyType.CNY));
//        try {
//            String status = wxPayService().refundV3(request).getStatus();
//            return WxPayConstants.RefundStatus.PROCESSING.equals(status) || WxPayConstants.RefundStatus.SUCCESS.equals(status);
//        } catch (WxPayException e) {
//            log.info("微信app支付(v3)异常: " + e);
//        }
//        return false;
//    }
//
//
//
//
//    /**
//     * 根据商户订单号查询订单支付状态
//     *
//     * @param orderNo 订单唯一编号(商户订单号)
//     * @return 支付状态
//     */
//    public static boolean queryOrderV3(String orderNo) {
//        try {
//            WxPayOrderQueryV3Result result = wxPayService().queryOrderV3(new WxPayOrderQueryV3Request().setOutTradeNo(orderNo));
//            log.info("查询订单结果(V3): {}", result);
//            return WxPayConstants.WxpayTradeStatus.SUCCESS.equals(result.getTradeState());
//        } catch (WxPayException e) {
//            log.info("查询订单结果异常(V3): " + e);
//        }
//        return false;
//    }
//
//
//    /**
//     * 根据商户订单号关闭订单
//     *
//     * @param orderNo 订单唯一编号(商户订单号)
//     */
//    public static void closeOrderV3(String orderNo) {
//        WxPayOrderCloseV3Request request = new WxPayOrderCloseV3Request();
//        request.setOutTradeNo(orderNo);
//        try {
//            wxPayService().closeOrderV3(request);
//        } catch (WxPayException e) {
//            log.info("关闭订单异常(V3): " + e);
//        }
//    }
//
//
//    /**
//     * 获取订单支付回调信息
//     */
//    public static WxPayNotifyV3Result.DecryptNotifyResult notifyV3Result(HttpServletRequest request) {
//        try {
//            return wxPayService()
//                    .parseOrderNotifyV3Result(RequestUtils.readData(request), SignatureHeader.builder()
//                            .timeStamp(request.getHeader("Wechatpay-TimeStamp"))
//                            .nonce(request.getHeader("Wechatpay-Nonce"))
//                            .signature(request.getHeader("Wechatpay-Signature"))
//                            .serial(request.getHeader("Wechatpay-Serial")).build())
//                    .getResult();
//        } catch (WxPayException e) {
//            log.info("获取订单支付回调信息异常(V3): " + e);
//            throw new ServiceException("获取订单支付回调信息异常code:"+e.getErrCode()+"msg:"+e.getCustomErrorMsg());
//        }
//    }
//
//
//    /**
//     * 交易结束时间(10分钟)
//     *
//     */
//    private static String timeExpire() {
//        Calendar calendar = Calendar.getInstance();
//        calendar.add(Calendar.MINUTE, 10);
//        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX").format(calendar.getTime());
//    }
//
//
//}
//
//
//
