package com.example.util;

import java.util.Map; 

import org.ksoap2.SoapEnvelope; 
import org.ksoap2.serialization.SoapObject; 
import org.ksoap2.serialization.SoapSerializationEnvelope; 
import org.ksoap2.transport.HttpTransportSE; 
import org.kxml2.kdom.Element; 
import org.kxml2.kdom.Node; 
  
/* 
 * WebService������(SoapObject or JSON) 
 * Author: S.Sams��MSN.COM 
 * Created by SamShum  2013-07-24 21:10 
 */
public class WebServiceUtil { 
      
    private Boolean _isdotnet = true; 
    /* 
     * ���õ�ǰWebServices�Ƿ�֧�� .net ��WebServices�� 
     * @param dotNetWebService: Ĭ��true; 
     */
    public WebServiceUtil setIsDotNet(Boolean dotNetWebService) 
    { 
        _isdotnet = dotNetWebService; 
        return this; 
    } 
      
    private int _setHttpTimeOut = 10* 1000; 
    /* 
     * ����HTTP�����ʱ�䣬��λ���룻 
     * @param secondTime: Ĭ�� 10 s 
     */
    public WebServiceUtil setHttpTimeOut(int secondTime) 
    { 
        _setHttpTimeOut = secondTime; 
        return this; 
    } 
      
    private Boolean _isdebug = false; 
    /* 
     * ��������HTTP��Debugģʽ 
     * @param isdebug: Ĭ�� false 
     */
    public WebServiceUtil setIsDebug(Boolean isdebug) 
    { 
        _isdebug = isdebug; 
        return this; 
    } 
      
    private Boolean _iswritelog = false; 
    /* 
     * �Ƿ������־ 
     * @param iswritelog: Ĭ�� false 
     */
    public WebServiceUtil setOutLog(Boolean iswritelog) 
    { 
        _iswritelog = iswritelog; 
        return this; 
    } 
      
    /* 
     * ��ȡWebService���ݣ������ַ���ʽ���ء� 
     * @param Url: WebService�����ַ (http://webservice.***.com.cn/WeatherWS.asmx) 
     * @param NameSpace: WebService�ķ���������ռ䣬����WSDL�������ҵ� (http://***.com.cn/) 
     * @param MethodName: WebService�ĵ��ú�����������(getDataMethod) 
     * @param Maps: ���������Ҫ�ύ�����ݼ� 
     * @Return: �������ַ����ͷ����������� 
     * @Exception: д�����̨��־ 
     */
    public String GetString(String Url, String NameSpace, String MethodName, Map<String, String>  RequestDatas){ 
        return GetString(Url, NameSpace, MethodName, RequestDatas, null, null); 
    } 
      
    /* 
     * ��ȡWebService���ݣ������ַ���ʽ���ء� 
     * @param Url: WebService�����ַ (http://webservice.***.com.cn/WeatherWS.asmx) 
     * @param NameSpace: WebService�ķ���������ռ䣬����WSDL�������ҵ� (http://***.com.cn/) 
     * @param MethodName: WebService�ĵ��ú�����������(getDataMethod) 
     * @param Maps: ���������Ҫ�ύ�����ݼ� 
     * @param SoapHeadeName: ����WebService��HTTPͷ���� 
     * @param SoapHeadeValues: ���� SoapHeade �����ݼ� 
     * @Return: �������ַ����ͷ����������� 
     * @Exception: д�����̨��־ 
     */
    public String GetString(String Url, String NameSpace, String MethodName, Map<String, String>  RequestDatas, String SoapHeadeName, Map<String, String> SoapHeadeValues)  { 
        SoapObject soap = GetObject(Url, NameSpace, MethodName, RequestDatas, SoapHeadeName, SoapHeadeValues); 
        if(soap != null && soap.getPropertyCount() > 0) 
        { 
            String getResultString = soap.getProperty(0).toString(); 
            if(_iswritelog) System.out.println("[Return Data] : "+ getResultString); 
            return getResultString; 
        } 
        return null; 
    } 
  
    /* 
     * ��ȡWebService���ݣ�����SoapObject���� 
     * @param Url: WebService�����ַ (http://webservice.***.com.cn/WeatherWS.asmx) 
     * @param NameSpace: WebService�ķ���������ռ䣬����WSDL�������ҵ� (http://***.com.cn/) 
     * @param MethodName: WebService�ĵ��ú�����������(getDataMethod) 
     * @param Maps: ���������Ҫ�ύ�����ݼ� 
     * @Return: ���񷵻�SoapObject���� 
     * @Exception: д�����̨��־ 
     */
    public SoapObject GetObject(String Url, String NameSpace, String MethodName,  Map<String, String>  RequestDatas) { 
        return GetObject(Url, NameSpace, MethodName, RequestDatas, null, null); 
    } 
      
    /* 
     * ��ȡWebService���ݣ�����SoapObject���� 
     * @param Url: WebService�����ַ (http://webservice.***.com.cn/WeatherWS.asmx) 
     * @param NameSpace: WebService�ķ���������ռ䣬����WSDL�������ҵ� (http://***.com.cn/) 
     * @param MethodName: WebService�ĵ��ú�����������(getDataMethod) 
     * @param Maps: ���������Ҫ�ύ�����ݼ� 
     * @param SoapHeadeName: ����WebService��HTTPͷ���� 
     * @param SoapHeadeValues: ���� SoapHeade �����ݼ� 
     * @Return: ���񷵻�SoapObject���� 
     * @Exception: д�����̨��־ 
     */
    public SoapObject GetObject(String Url, String NameSpace, String MethodName, Map<String, String>  RequestDatas, String SoapHeadeName, Map<String, String> SoapHeadeValues) { 
        try { 
              
            SoapObject soap = new SoapObject(NameSpace, MethodName); 
              
            // ϵͳ��־��� 
            if(_iswritelog) System.out.println("[URL] : "   + Url); 
            if(_iswritelog) System.out.println("[NameSpace] : " + NameSpace); 
            if(_iswritelog) System.out.println("[MethodName] : "    + MethodName); 
            if(_iswritelog) System.out.println("[SOAP Action] : "+ NameSpace+MethodName); 
      
            // ����WebService�ύ�����ݼ� 
            if (RequestDatas != null && !RequestDatas.isEmpty()) { 
                for (Map.Entry<String, String> entry : RequestDatas.entrySet()) { 
                    soap.addProperty(entry.getKey(),  entry.getValue()); 
                } 
            } 
      
            // ����HTTPͷ��Ϣ 
            Element[] header = null; 
            if(SoapHeadeName != null && SoapHeadeValues != null && !SoapHeadeValues.isEmpty()) 
            { 
                    header = new Element[1]; 
                    header[0] = new Element().createElement(NameSpace, SoapHeadeName); 
                  
                    for (Map.Entry<String, String> entry : SoapHeadeValues.entrySet()) { 
                        Element element = new Element().createElement(NameSpace, entry.getKey()); 
                        element.addChild(Node.TEXT, entry.getValue()); 
                        header[0].addChild(Node.ELEMENT, element); 
                } 
            }    
      
            // ��ʼ���������� 
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11); 
            envelope.dotNet = _isdotnet; 
            if(header  != null)envelope.headerOut = header; 
            envelope.bodyOut = soap; 
            envelope.setOutputSoapObject(soap);  
              
            // ����Web���� 
            HttpTransportSE http = new HttpTransportSE(Url, _setHttpTimeOut); 
            http.debug = _isdebug; 
            http.call(NameSpace+MethodName , envelope); 
              
            // ��ȡWeb�������� ������Ҫ�� result.getProperty(0) ��ȡ 
            SoapObject result = (SoapObject) envelope.bodyIn; 
              
            if(_iswritelog) System.out.println("[SOAP.getPropertyCount] : " + result.getPropertyCount()); 
              
            return result; 
  
        } catch (Exception e) { 
            if(_iswritelog) System.err.println("[Http Exception] : "    + e.getMessage()); 
        } 
        return null; 
    } 
  
  
} 
