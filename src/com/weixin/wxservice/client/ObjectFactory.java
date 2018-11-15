
package com.weixin.wxservice.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.shdt.wxservice.client package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _TemplateMessageResponse_QNAME = new QName("http://wxservice.shdt.com/", "templateMessageResponse");
    private final static QName _SetTagsResponse_QNAME = new QName("http://wxservice.shdt.com/", "setTagsResponse");
    private final static QName _CreateMenuResponse_QNAME = new QName("http://wxservice.shdt.com/", "createMenuResponse");
    private final static QName _DeleteMenuResponse_QNAME = new QName("http://wxservice.shdt.com/", "deleteMenuResponse");
    private final static QName _CreateKeFuResponse_QNAME = new QName("http://wxservice.shdt.com/", "createKeFuResponse");
    private final static QName _GetTagListResponse_QNAME = new QName("http://wxservice.shdt.com/", "getTagListResponse");
    private final static QName _CreateKeFu_QNAME = new QName("http://wxservice.shdt.com/", "createKeFu");
    private final static QName _GetTagList_QNAME = new QName("http://wxservice.shdt.com/", "getTagList");
    private final static QName _CreateMenu_QNAME = new QName("http://wxservice.shdt.com/", "createMenu");
    private final static QName _ReturnCustomMessge_QNAME = new QName("http://wxservice.shdt.com/", "returnCustomMessge");
    private final static QName _GetUserListResponse_QNAME = new QName("http://wxservice.shdt.com/", "getUserListResponse");
    private final static QName _ReturnMessgeResponse_QNAME = new QName("http://wxservice.shdt.com/", "returnMessgeResponse");
    private final static QName _SendTemplateDataResponse_QNAME = new QName("http://wxservice.shdt.com/", "SendTemplateDataResponse");
    private final static QName _ReturnMessge_QNAME = new QName("http://wxservice.shdt.com/", "returnMessge");
    private final static QName _SetTags_QNAME = new QName("http://wxservice.shdt.com/", "setTags");
    private final static QName _TemplateMessage_QNAME = new QName("http://wxservice.shdt.com/", "templateMessage");
    private final static QName _MessageKeFu_QNAME = new QName("http://wxservice.shdt.com/", "MessageKeFu");
    private final static QName _SendTemplateData_QNAME = new QName("http://wxservice.shdt.com/", "SendTemplateData");
    private final static QName _MessageKeFuResponse_QNAME = new QName("http://wxservice.shdt.com/", "MessageKeFuResponse");
    private final static QName _DeleteMenu_QNAME = new QName("http://wxservice.shdt.com/", "deleteMenu");
    private final static QName _GetUserList_QNAME = new QName("http://wxservice.shdt.com/", "getUserList");
    private final static QName _ReturnCustomMessgeResponse_QNAME = new QName("http://wxservice.shdt.com/", "returnCustomMessgeResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.shdt.wxservice.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SetTags }
     * 
     */
    public SetTags createSetTags() {
        return new SetTags();
    }

    /**
     * Create an instance of {@link TemplateMessage }
     * 
     */
    public TemplateMessage createTemplateMessage() {
        return new TemplateMessage();
    }

    /**
     * Create an instance of {@link MessageKeFu }
     * 
     */
    public MessageKeFu createMessageKeFu() {
        return new MessageKeFu();
    }

    /**
     * Create an instance of {@link SendTemplateData }
     * 
     */
    public SendTemplateData createSendTemplateData() {
        return new SendTemplateData();
    }

    /**
     * Create an instance of {@link GetUserList }
     * 
     */
    public GetUserList createGetUserList() {
        return new GetUserList();
    }

    /**
     * Create an instance of {@link ReturnCustomMessgeResponse }
     * 
     */
    public ReturnCustomMessgeResponse createReturnCustomMessgeResponse() {
        return new ReturnCustomMessgeResponse();
    }

    /**
     * Create an instance of {@link MessageKeFuResponse }
     * 
     */
    public MessageKeFuResponse createMessageKeFuResponse() {
        return new MessageKeFuResponse();
    }

    /**
     * Create an instance of {@link DeleteMenu }
     * 
     */
    public DeleteMenu createDeleteMenu() {
        return new DeleteMenu();
    }

    /**
     * Create an instance of {@link TemplateMessageResponse }
     * 
     */
    public TemplateMessageResponse createTemplateMessageResponse() {
        return new TemplateMessageResponse();
    }

    /**
     * Create an instance of {@link SetTagsResponse }
     * 
     */
    public SetTagsResponse createSetTagsResponse() {
        return new SetTagsResponse();
    }

    /**
     * Create an instance of {@link CreateMenuResponse }
     * 
     */
    public CreateMenuResponse createCreateMenuResponse() {
        return new CreateMenuResponse();
    }

    /**
     * Create an instance of {@link GetTagListResponse }
     * 
     */
    public GetTagListResponse createGetTagListResponse() {
        return new GetTagListResponse();
    }

    /**
     * Create an instance of {@link DeleteMenuResponse }
     * 
     */
    public DeleteMenuResponse createDeleteMenuResponse() {
        return new DeleteMenuResponse();
    }

    /**
     * Create an instance of {@link CreateKeFuResponse }
     * 
     */
    public CreateKeFuResponse createCreateKeFuResponse() {
        return new CreateKeFuResponse();
    }

    /**
     * Create an instance of {@link ReturnCustomMessge }
     * 
     */
    public ReturnCustomMessge createReturnCustomMessge() {
        return new ReturnCustomMessge();
    }

    /**
     * Create an instance of {@link CreateMenu }
     * 
     */
    public CreateMenu createCreateMenu() {
        return new CreateMenu();
    }

    /**
     * Create an instance of {@link ReturnMessgeResponse }
     * 
     */
    public ReturnMessgeResponse createReturnMessgeResponse() {
        return new ReturnMessgeResponse();
    }

    /**
     * Create an instance of {@link GetUserListResponse }
     * 
     */
    public GetUserListResponse createGetUserListResponse() {
        return new GetUserListResponse();
    }

    /**
     * Create an instance of {@link CreateKeFu }
     * 
     */
    public CreateKeFu createCreateKeFu() {
        return new CreateKeFu();
    }

    /**
     * Create an instance of {@link GetTagList }
     * 
     */
    public GetTagList createGetTagList() {
        return new GetTagList();
    }

    /**
     * Create an instance of {@link ReturnMessge }
     * 
     */
    public ReturnMessge createReturnMessge() {
        return new ReturnMessge();
    }

    /**
     * Create an instance of {@link SendTemplateDataResponse }
     * 
     */
    public SendTemplateDataResponse createSendTemplateDataResponse() {
        return new SendTemplateDataResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TemplateMessageResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://wxservice.shdt.com/", name = "templateMessageResponse")
    public JAXBElement<TemplateMessageResponse> createTemplateMessageResponse(TemplateMessageResponse value) {
        return new JAXBElement<TemplateMessageResponse>(_TemplateMessageResponse_QNAME, TemplateMessageResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SetTagsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://wxservice.shdt.com/", name = "setTagsResponse")
    public JAXBElement<SetTagsResponse> createSetTagsResponse(SetTagsResponse value) {
        return new JAXBElement<SetTagsResponse>(_SetTagsResponse_QNAME, SetTagsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateMenuResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://wxservice.shdt.com/", name = "createMenuResponse")
    public JAXBElement<CreateMenuResponse> createCreateMenuResponse(CreateMenuResponse value) {
        return new JAXBElement<CreateMenuResponse>(_CreateMenuResponse_QNAME, CreateMenuResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteMenuResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://wxservice.shdt.com/", name = "deleteMenuResponse")
    public JAXBElement<DeleteMenuResponse> createDeleteMenuResponse(DeleteMenuResponse value) {
        return new JAXBElement<DeleteMenuResponse>(_DeleteMenuResponse_QNAME, DeleteMenuResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateKeFuResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://wxservice.shdt.com/", name = "createKeFuResponse")
    public JAXBElement<CreateKeFuResponse> createCreateKeFuResponse(CreateKeFuResponse value) {
        return new JAXBElement<CreateKeFuResponse>(_CreateKeFuResponse_QNAME, CreateKeFuResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTagListResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://wxservice.shdt.com/", name = "getTagListResponse")
    public JAXBElement<GetTagListResponse> createGetTagListResponse(GetTagListResponse value) {
        return new JAXBElement<GetTagListResponse>(_GetTagListResponse_QNAME, GetTagListResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateKeFu }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://wxservice.shdt.com/", name = "createKeFu")
    public JAXBElement<CreateKeFu> createCreateKeFu(CreateKeFu value) {
        return new JAXBElement<CreateKeFu>(_CreateKeFu_QNAME, CreateKeFu.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTagList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://wxservice.shdt.com/", name = "getTagList")
    public JAXBElement<GetTagList> createGetTagList(GetTagList value) {
        return new JAXBElement<GetTagList>(_GetTagList_QNAME, GetTagList.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateMenu }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://wxservice.shdt.com/", name = "createMenu")
    public JAXBElement<CreateMenu> createCreateMenu(CreateMenu value) {
        return new JAXBElement<CreateMenu>(_CreateMenu_QNAME, CreateMenu.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReturnCustomMessge }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://wxservice.shdt.com/", name = "returnCustomMessge")
    public JAXBElement<ReturnCustomMessge> createReturnCustomMessge(ReturnCustomMessge value) {
        return new JAXBElement<ReturnCustomMessge>(_ReturnCustomMessge_QNAME, ReturnCustomMessge.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserListResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://wxservice.shdt.com/", name = "getUserListResponse")
    public JAXBElement<GetUserListResponse> createGetUserListResponse(GetUserListResponse value) {
        return new JAXBElement<GetUserListResponse>(_GetUserListResponse_QNAME, GetUserListResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReturnMessgeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://wxservice.shdt.com/", name = "returnMessgeResponse")
    public JAXBElement<ReturnMessgeResponse> createReturnMessgeResponse(ReturnMessgeResponse value) {
        return new JAXBElement<ReturnMessgeResponse>(_ReturnMessgeResponse_QNAME, ReturnMessgeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendTemplateDataResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://wxservice.shdt.com/", name = "SendTemplateDataResponse")
    public JAXBElement<SendTemplateDataResponse> createSendTemplateDataResponse(SendTemplateDataResponse value) {
        return new JAXBElement<SendTemplateDataResponse>(_SendTemplateDataResponse_QNAME, SendTemplateDataResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReturnMessge }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://wxservice.shdt.com/", name = "returnMessge")
    public JAXBElement<ReturnMessge> createReturnMessge(ReturnMessge value) {
        return new JAXBElement<ReturnMessge>(_ReturnMessge_QNAME, ReturnMessge.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SetTags }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://wxservice.shdt.com/", name = "setTags")
    public JAXBElement<SetTags> createSetTags(SetTags value) {
        return new JAXBElement<SetTags>(_SetTags_QNAME, SetTags.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TemplateMessage }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://wxservice.shdt.com/", name = "templateMessage")
    public JAXBElement<TemplateMessage> createTemplateMessage(TemplateMessage value) {
        return new JAXBElement<TemplateMessage>(_TemplateMessage_QNAME, TemplateMessage.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MessageKeFu }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://wxservice.shdt.com/", name = "MessageKeFu")
    public JAXBElement<MessageKeFu> createMessageKeFu(MessageKeFu value) {
        return new JAXBElement<MessageKeFu>(_MessageKeFu_QNAME, MessageKeFu.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendTemplateData }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://wxservice.shdt.com/", name = "SendTemplateData")
    public JAXBElement<SendTemplateData> createSendTemplateData(SendTemplateData value) {
        return new JAXBElement<SendTemplateData>(_SendTemplateData_QNAME, SendTemplateData.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MessageKeFuResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://wxservice.shdt.com/", name = "MessageKeFuResponse")
    public JAXBElement<MessageKeFuResponse> createMessageKeFuResponse(MessageKeFuResponse value) {
        return new JAXBElement<MessageKeFuResponse>(_MessageKeFuResponse_QNAME, MessageKeFuResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteMenu }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://wxservice.shdt.com/", name = "deleteMenu")
    public JAXBElement<DeleteMenu> createDeleteMenu(DeleteMenu value) {
        return new JAXBElement<DeleteMenu>(_DeleteMenu_QNAME, DeleteMenu.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://wxservice.shdt.com/", name = "getUserList")
    public JAXBElement<GetUserList> createGetUserList(GetUserList value) {
        return new JAXBElement<GetUserList>(_GetUserList_QNAME, GetUserList.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReturnCustomMessgeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://wxservice.shdt.com/", name = "returnCustomMessgeResponse")
    public JAXBElement<ReturnCustomMessgeResponse> createReturnCustomMessgeResponse(ReturnCustomMessgeResponse value) {
        return new JAXBElement<ReturnCustomMessgeResponse>(_ReturnCustomMessgeResponse_QNAME, ReturnCustomMessgeResponse.class, null, value);
    }

}
