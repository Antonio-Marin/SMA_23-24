import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;

/**
 * class Mensaje : Clase que almacena la información asociada a un mensaje
 * @author MAFG y Varios alumnos 2022-2023
 * @fechaDeCreacion: 2022-xx-xx
 * @fechaDeUltimaModificacion: 2023-10-04
 *      Ajustes para el curso 2023-2024 (MAFG)
 * @version: 2023-2024-01
 * @observaciones:
 */
public class Mensaje {

    protected String comuncId;
    protected String msgId; // IP del agente que envía el mensaje
    protected String typeProtocol; //Indica el protcolo del mensaje (intercambio, etc.)
    protected String protocolStep; //paso del protocolo
    protected String comunicationProtocol;  // Se admiten protocolos TCP y UDP
    protected String originId; // Identificador único del agente que envía el mensaje
    protected String originIp; // IP del agente que envía el mensaje

    protected String originPortUDP; // Puerto del agente que envía el mensaje
    protected String originPortTCP ; // Puerto del agente que envía el mensaje
    protected String originTime ;

    protected String destinationId; // Identificador único del agente destino de este mensaje
    protected String destinationIp; // IP del agente destino de este mensaje
    protected String destinationPortUDP;
    protected String destinationPortTCP;
    protected String destinationTime;
    protected String bodyInfo;  //Contenido del mensaje a enviar. s el contenido de este campo, lo que viajara al destino
    protected String deathReason;
    protected ArrayList<String> ownedCardType;
    protected ArrayList<String> ownedCardQuantity;
    protected ArrayList<String> ownedCardCost;
    protected ArrayList<String> wantedCardType;
    protected String ownedMoney;
    protected String createdChilds;
    protected String deathTime;
    protected String pastTradeWantedCard;
    protected String pastTradeGivenCard;
    protected String tradeWantedCard;
    protected String tradeGivenCard;
    protected String tradeMoney;

    protected ArrayList<String> offeredCardType;
    protected ArrayList<String> offeredCardQuantity;
    protected ArrayList<String> offeredCardCost;
    protected ArrayList<String> wishedCardType;
    protected ArrayList<AccTest> agentsDirectory;
    protected ArrayList<AccTest> deadAgents;

//CONSTRUCTOR DE MENSAJE


    public Mensaje(String comuncId, String msgId, String typeProtocol, String protocolStep, String comunicationProtocol,
                   String originId, String originIp, String originPortUDP, String originPortTCP, String originTime,
                   String destinationId, String destinationIp, String destinationPortUDP, String destinationPortTCP, String destinationTime)
    {
        this.comuncId = comuncId;
        this.msgId = msgId;
        this.typeProtocol = typeProtocol;
        this.protocolStep = protocolStep;
        this.comunicationProtocol = comunicationProtocol;
        this.originId = originId;
        this.originIp = originIp;
        this.originPortUDP = originPortUDP;
        this.originPortTCP = originPortTCP;
        this.originTime = originTime;
        this.destinationId = destinationId;
        this.destinationIp = destinationIp;
        this.destinationPortUDP = destinationPortUDP;
        this.destinationPortTCP = destinationPortTCP;
        this.destinationTime = destinationTime;
    }

    //GETTERS
    public String getComuncId() {
        return comuncId;
    }

    public String getMsgId() {
        return msgId;
    }

    public String getTypeProtocol() {
        return typeProtocol;
    }

    public String getProtocolStep() {
        return protocolStep;
    }

    public String getComunicationProtocol() {
        return comunicationProtocol;
    }

    public String getOriginId() {
        return originId;
    }

    public String getOriginIp() {
        return originIp;
    }

    public String getOriginPortUDP() {
        return originPortUDP;
    }

    public String getOriginPortTCP() {
        return originPortTCP;
    }

    public String getOriginTime() {
        return originTime;
    }

    public String getDestinationId() {
        return destinationId;
    }

    public String getDestinationIp() {
        return destinationIp;
    }

    public String getDestinationPortUDP() {
        return destinationPortUDP;
    }

    public String getDestinationPortTCP() {
        return destinationPortTCP;
    }

    public String getDestinationTime() {
        return destinationTime;
    }
    public String getBodyInfo() {
        return bodyInfo;
    }
    public String getDeathReason() {
        return deathReason;
    }
    public ArrayList<String> getOwnedCardType() {
        return ownedCardType;
    }

    public ArrayList<String> getOwnedCardQuantity() {
        return ownedCardQuantity;
    }

    public ArrayList<String> getOwnedCardCost() {
        return ownedCardCost;
    }

    public ArrayList<String> getWantedCardType() {
        return wantedCardType;
    }

    public String getOwnedMoney() {
        return ownedMoney;
    }

    public String getCreatedChilds() {
        return createdChilds;
    }

    public String getDeathTime() {
        return deathTime;
    }

    public String getPastTradeWantedCard() {
        return pastTradeWantedCard;
    }

    public String getPastTradeGivenCard() {
        return pastTradeGivenCard;
    }

    public String getTradeWantedCard() {
        return tradeWantedCard;
    }

    public String getTradeGivenCard() {
        return tradeGivenCard;
    }

    public String getTradeMoney() {
        return tradeMoney;
    }

    public ArrayList<String> getOfferedCardType() {
        return offeredCardType;
    }

    public ArrayList<String> getOfferedCardQuantity() {
        return offeredCardQuantity;
    }

    public ArrayList<String> getOfferedCardCost() {
        return offeredCardCost;
    }

    public ArrayList<String> getWishedCardType() {
        return wishedCardType;
    }
    public ArrayList<AccTest> getAgentsDirectory() {
        return agentsDirectory;
    }

    public ArrayList<AccTest> getDeadAgents() {
        return deadAgents;
    }

    //SETTERS
    public void setBodyInfo(String bodyInfo) {
        this.bodyInfo = bodyInfo;
    }
    public void setDeathReason(String deathReason) {
        this.deathReason = deathReason;
    }
    public void setOwnedCardType(ArrayList<String> ownedCardType) {
        this.ownedCardType = ownedCardType;
    }

    public void setOwnedCardQuantity(ArrayList<String> ownedCardQuantity) {
        this.ownedCardQuantity = ownedCardQuantity;
    }

    public void setOwnedCardCost(ArrayList<String> ownedCardCost) {
        this.ownedCardCost = ownedCardCost;
    }

    public void setWantedCardType(ArrayList<String> wantedCardType) {
        this.wantedCardType = wantedCardType;
    }

    public void setOwnedMoney(String ownedMoney) {
        this.ownedMoney = ownedMoney;
    }

    public void setCreatedChilds(String createdChilds) {
        this.createdChilds = createdChilds;
    }

    public void setDeathTime(String deathTime) {
        this.deathTime = deathTime;
    }

    public void setPastTradeWantedCard(String pastTradeWantedCard) {
        this.pastTradeWantedCard = pastTradeWantedCard;
    }

    public void setPastTradeGivenCard(String pastTradeGivenCard) {
        this.pastTradeGivenCard = pastTradeGivenCard;
    }

    public void setTradeWantedCard(String tradeWantedCard) {
        this.tradeWantedCard = tradeWantedCard;
    }

    public void setTradeGivenCard(String tradeGivenCard) {
        this.tradeGivenCard = tradeGivenCard;
    }

    public void setTradeMoney(String tradeMoney) {
        this.tradeMoney = tradeMoney;
    }

    public void setOfferedCardType(ArrayList<String> offeredCardType) {
        this.offeredCardType = offeredCardType;
    }

    public void setOfferedCardQuantity(ArrayList<String> offeredCardQuantity) {
        this.offeredCardQuantity = offeredCardQuantity;
    }

    public void setOfferedCardCost(ArrayList<String> offeredCardCost) {
        this.offeredCardCost = offeredCardCost;
    }

    public void setWishedCardType(ArrayList<String> wishedCardType) {
        this.wishedCardType = wishedCardType;
    }
    public void setAgentsDirectory(ArrayList<AccTest> agentsDirectory) {
        this.agentsDirectory = agentsDirectory;
    }

    public void setDeadAgents(ArrayList<AccTest> deadAgents) {
        this.deadAgents = deadAgents;
    }

    /**
     * CREAR XML
     * Método que sirve para crear XML con los
     * atributos de la clase mensaje
     */
    public void crearXML(){
        try {
            //System.out.println("Llego hasta aquí 1");

            // Crear un DocumentBuilder
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Crear un Document
            Document doc = builder.newDocument();

            // Crear elementos y construir la estructura
            Element messageElement = doc.createElement("Message");
            doc.appendChild(messageElement);

            Element comuncIdElement = doc.createElement("comunc_id");
            comuncIdElement.appendChild(doc.createTextNode(this.comuncId));
            messageElement.appendChild(comuncIdElement);

            Element msgIdElement = doc.createElement("msg_id");
            msgIdElement.appendChild(doc.createTextNode(this.msgId));
            messageElement.appendChild(msgIdElement);

            // Crear el elemento header y agregarlo a Message
            Element headerElement = doc.createElement("header");
            messageElement.appendChild(headerElement);

            // Añadir los elementos dentro de header
            Element typeProtocolElement = doc.createElement("type_protocol");
            typeProtocolElement.appendChild(doc.createTextNode(this.typeProtocol));
            headerElement.appendChild(typeProtocolElement);

            Element protocolStepElement = doc.createElement("protocol_step");
            protocolStepElement.appendChild(doc.createTextNode(this.protocolStep));
            headerElement.appendChild(protocolStepElement);
            //System.out.println("Llego hasta aquí 2");

            Element comunicationProtocolElement = doc.createElement("comunication_protocol");
            comunicationProtocolElement.appendChild(doc.createTextNode(this.comunicationProtocol));
            headerElement.appendChild(comunicationProtocolElement);

            Element originElement = doc.createElement("origin");
            headerElement.appendChild(originElement);

            Element originIdElement = doc.createElement("origin_id");
            originIdElement.appendChild(doc.createTextNode(this.originId));
            originElement.appendChild(originIdElement);

            Element originIpElement = doc.createElement("origin_ip");
            originIpElement.appendChild(doc.createTextNode(this.originIp));
            originElement.appendChild(originIpElement);

            Element originPortUDPElement = doc.createElement("origin_port_UDP");
            originPortUDPElement.appendChild(doc.createTextNode(this.originPortUDP));
            originElement.appendChild(originPortUDPElement);

            Element originPortTCPElement = doc.createElement("origin_port_TCP");
            originPortTCPElement.appendChild(doc.createTextNode(this.originPortTCP));
            originElement.appendChild(originPortTCPElement);

            Element originTimeElement = doc.createElement("origin_time");
            originTimeElement.appendChild(doc.createTextNode(this.originTime));
            originElement.appendChild(originTimeElement);
            //System.out.println("Llego hasta aquí 3");

            // Crear el elemento destination y agregarlo a header
            Element destinationElement = doc.createElement("destination");
            headerElement.appendChild(destinationElement);

            Element destinationIdElement = doc.createElement("destination_id");
            destinationIdElement.appendChild(doc.createTextNode(this.destinationId));
            destinationElement.appendChild(destinationIdElement);

            Element destinationIpElement = doc.createElement("destination_ip");
            destinationIpElement.appendChild(doc.createTextNode(this.destinationIp));
            destinationElement.appendChild(destinationIpElement);

            Element destinationPortUDPElement = doc.createElement("destination_port_UDP");
            destinationPortUDPElement.appendChild(doc.createTextNode(this.destinationPortUDP));
            destinationElement.appendChild(destinationPortUDPElement);

            Element destinationPortTCPElement = doc.createElement("destination_port_TCP");
            destinationPortTCPElement.appendChild(doc.createTextNode(this.destinationPortTCP));
            destinationElement.appendChild(destinationPortTCPElement);

            Element destinationTimeElement = doc.createElement("destination_time");
            destinationTimeElement.appendChild(doc.createTextNode(this.destinationTime));
            destinationElement.appendChild(destinationTimeElement);

            Element bodyElement = doc.createElement("body");
            messageElement.appendChild(bodyElement);

            Element bodyInfoElement = doc.createElement("body_info");
            bodyInfoElement.appendChild(doc.createTextNode(this.bodyInfo));
            bodyElement.appendChild(bodyInfoElement);

            Element deathContentElement = doc.createElement("death_content");
            bodyElement.appendChild(deathContentElement);

            Element deathReasonElement = doc.createElement("death_reason");
            if(this.deathReason != "") {
                bodyInfoElement.appendChild(doc.createTextNode(this.deathReason));
            } else {bodyInfoElement.appendChild(doc.createTextNode("0"));}
            deathContentElement.appendChild(deathReasonElement);

            // Agrega los elementos dentro de death_reason si es necesario

            Element deathAgentInfoElement = doc.createElement("death_agent_info");
            deathContentElement.appendChild(deathAgentInfoElement);

            Element ownedCardsElement = doc.createElement("owned_cards");
            deathAgentInfoElement.appendChild(ownedCardsElement);
            if(this.ownedCardType != null) {
                for (int i = 1; i <= this.ownedCardType.size(); i++) {
                    String text = "owned_card" + i;
                    Element ownedCardElement = doc.createElement(text);
                    ownedCardsElement.appendChild(ownedCardElement);

                    Element ownedCardTypeElement = doc.createElement("owned_card_type");
                    ownedCardTypeElement.appendChild(doc.createTextNode(this.ownedCardType.get(i - 1)));
                    ownedCardElement.appendChild(ownedCardTypeElement);

                    Element ownedCardQuantityElement = doc.createElement("owned_card_quantity");
                    ownedCardQuantityElement.appendChild(doc.createTextNode(this.ownedCardQuantity.get(i - 1)));
                    ownedCardElement.appendChild(ownedCardQuantityElement);

                    Element ownedCardCostElement = doc.createElement("owned_card_cost");
                    ownedCardCostElement.appendChild(doc.createTextNode(this.ownedCardCost.get(i - 1)));
                    ownedCardElement.appendChild(ownedCardCostElement);

                }
            }else{
                    String text = "owned_card";
                    Element ownedCardElement = doc.createElement(text);
                    ownedCardsElement.appendChild(ownedCardElement);

                    Element ownedCardTypeElement = doc.createElement("owned_card_type");
                    ownedCardElement.appendChild(ownedCardTypeElement);

                    Element ownedCardQuantityElement = doc.createElement("owned_card_quantity");
                    ownedCardElement.appendChild(ownedCardQuantityElement);

                    Element ownedCardCostElement = doc.createElement("owned_card_cost");
                    ownedCardElement.appendChild(ownedCardCostElement);


                }
            Element wantedCardsElement = doc.createElement("wanted_cards");
            deathAgentInfoElement.appendChild(wantedCardsElement);
            if(this.wantedCardType != null) {
                for (int i = 1; i <= this.wantedCardType.size(); i++) {
                    String text2 = "wanted_card" + i;
                    Element wantedCardElement = doc.createElement(text2);
                    wantedCardsElement.appendChild(wantedCardElement);

                    Element wantedCardTypeElement = doc.createElement("wanted_card_type");
                    wantedCardTypeElement.appendChild(doc.createTextNode(this.wantedCardType.get(i - 1)));
                    wantedCardElement.appendChild(wantedCardTypeElement);
                }
            }

            Element ownedMoneyElement = doc.createElement("owned_money");
            ownedMoneyElement.appendChild(doc.createTextNode(ownedMoney));
            deathAgentInfoElement.appendChild(ownedMoneyElement);

            Element createdChildsElement = doc.createElement("created_childs");
            createdChildsElement.appendChild(doc.createTextNode(createdChilds));
            deathAgentInfoElement.appendChild(createdChildsElement);

            Element deathTimeElement = doc.createElement("death_time");
            deathTimeElement.appendChild(doc.createTextNode(deathTime));
            deathAgentInfoElement.appendChild(deathTimeElement);

            Element tradeContentElement = doc.createElement("trade_content");
            bodyElement.appendChild(tradeContentElement);

            Element pastTradeWantedCardElement = doc.createElement("past_trade_wanted_card");
            pastTradeWantedCardElement.appendChild(doc.createTextNode(pastTradeWantedCard));
            tradeContentElement.appendChild(pastTradeWantedCardElement);

            Element pastTradeGivenCardElement = doc.createElement("past_trade_given_card");
            pastTradeGivenCardElement.appendChild(doc.createTextNode(pastTradeGivenCard));
            tradeContentElement.appendChild(pastTradeGivenCardElement);

            Element tradeWantedCardElement = doc.createElement("trade_wanted_card");
            tradeWantedCardElement.appendChild(doc.createTextNode(tradeWantedCard));
            tradeContentElement.appendChild(tradeWantedCardElement);

            Element tradeGivenCardElement = doc.createElement("trade_given_card");
            tradeGivenCardElement.appendChild(doc.createTextNode(tradeGivenCard));
            tradeContentElement.appendChild(tradeGivenCardElement);

            Element offeredCardsElement = doc.createElement("offered_cards");
            tradeContentElement.appendChild(offeredCardsElement);
            if(this.offeredCardType!=null) {
                for (int i = 1; i <= this.offeredCardType.size(); i++) {
                    String text3 = "offered_card" + i;
                    Element offeredCard1Element = doc.createElement(text3);
                    offeredCardsElement.appendChild(offeredCard1Element);

                    Element offeredCard1TypeElement = doc.createElement("offered_card_type");
                    offeredCard1TypeElement.appendChild(doc.createTextNode(this.offeredCardType.get(i - 1)));
                    offeredCard1Element.appendChild(offeredCard1TypeElement);

                    Element offeredCard1QuantityElement = doc.createElement("offered_card_quantity");
                    offeredCard1QuantityElement.appendChild(doc.createTextNode(this.offeredCardQuantity.get(i - 1)));
                    offeredCard1Element.appendChild(offeredCard1QuantityElement);

                    Element offeredCard1CostElement = doc.createElement("offered_card_cost");
                    offeredCard1CostElement.appendChild(doc.createTextNode(this.offeredCardCost.get(i - 1)));
                    offeredCard1Element.appendChild(offeredCard1CostElement);
                }
            }

            Element wishedCardsElement = doc.createElement("wished_cards");
            tradeContentElement.appendChild(wishedCardsElement);
            if(this.wishedCardType!=null) {
                for (int i = 1; i <= this.wishedCardType.size(); i++) {
                    String text4 = "wished_card" + i;
                    Element wishedCardElement = doc.createElement(text4);
                    wishedCardsElement.appendChild(wishedCardElement);

                    Element wishedCardTypeElement = doc.createElement("wished_card_type");
                    wishedCardTypeElement.appendChild(doc.createTextNode(this.wishedCardType.get(i - 1)));
                    wishedCardElement.appendChild(wishedCardTypeElement);

                }
            }

            Element tradeMoneyElement = doc.createElement("trade_money");
            tradeMoneyElement.appendChild(doc.createTextNode(this.tradeMoney));
            tradeContentElement.appendChild(tradeMoneyElement);

            Element commonContentElement = doc.createElement("common_content");
            messageElement.appendChild(commonContentElement);

            Element agentsDirectoryElement = doc.createElement("agents_directory");
            commonContentElement.appendChild(agentsDirectoryElement);

            if(this.agentsDirectory!=null) {
                for (int i = 1; i <= this.agentsDirectory.size(); i++) {
                    String text5 = "acc" + i;

                    Element accElement = doc.createElement(text5);
                    agentsDirectoryElement.appendChild(accElement);

                    Element idAccElement = doc.createElement("id");
                    idAccElement.appendChild(doc.createTextNode(this.agentsDirectory.get(i - 1).ID_propio));
                    accElement.appendChild(idAccElement);

                    Element portAccElement = doc.createElement("port");
                    portAccElement.appendChild(doc.createTextNode(Integer.toString(this.agentsDirectory.get(i - 1).Puerto_Propio)));
                    accElement.appendChild(portAccElement);

                    Element ipAccElement = doc.createElement("ip");
                    ipAccElement.appendChild(doc.createTextNode(this.agentsDirectory.get(i - 1).Ip_Propia));
                    accElement.appendChild(ipAccElement);

                }
            }

            Element deadAgentsElement = doc.createElement("dead_agents");
            commonContentElement.appendChild(deadAgentsElement);
            if(this.deadAgents!=null) {
                for (int i = 1; i <= this.deadAgents.size(); i++) {
                    String text6 = "dead_acc" + i;

                    Element deadAccElement = doc.createElement(text6);
                    deadAgentsElement.appendChild(deadAccElement);

                    Element idAccElement = doc.createElement("id");
                    idAccElement.appendChild(doc.createTextNode(this.deadAgents.get(i - 1).ID_propio));
                    deadAccElement.appendChild(idAccElement);
                }
            }

            // Escribir el DOM en un archivo XML
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            // Configuración de la salida
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
            //System.out.println("Llego hasta aquí");
            File file = new File("xml_"+comuncId+".xml");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(file);

            transformer.transform(source, result);

            System.out.println("Archivo XML creado correctamente.");
        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }
    }

    /**
     * PRUEBA DE CREADO XML
     * Guardado para usarlo como base
     */
    /*
    si algún elemento lo crea asi <blablabla/> esta bien no es un error, se ve que asi es como XML
    representa elementos vacios, por eso no hace falta el <blablabla></blablabla> porque no tiene ningun valor dentro
    */
    public void prueba(){
        try {
            String comuncId = "1";
            String msgId = "1";
            String typeProtocol = "Intercambio";
            String protocolStep = "Oferta";
            String comunicationProtocol = "UDP";
            String originId = "ag1_id";
            String originIp = "1.1.1.1";
            String originPortUDP = "10";
            String originPortTCP = "10";
            String originTime = "12/10/2023 20:45:35";
            String destinationId = "ag2_id";
            String destinationIp = "2.2.2.2";
            String destinationPortUDP = "10";
            String destinationPortTCP = "10";
            String destinationTime = "12/10/2023 20:45:35";
            String bodyInfo = "Mensaje de texto";
            String ownedCard1Type = "Pikachu";
            String ownedCard1Quantity = "3";
            String ownedCard1Cost = "10";
            String ownedCard2Type = "Chikorita";
            String ownedCard2Quantity = "2";
            String ownedCard2Cost = "20";
            String wantedCard1Type = "Mew";
            String wantedCard2Type = "Bidoof";
            String ownedMoney = "200";
            String createdChilds = "20";
            String deathTime = "-";
            String pastTradeWantedCard = "-";
            String pastTradeGivenCard = "-";
            String tradeWantedCard = "X";
            String tradeGivenCard = "Y";
            String tradeMoney = "20";

            String offeredCard1Type = "Pikachu";
            String offeredCard1Quantity = "3";
            String offeredCard1Cost = "10";
            String offeredCard2Type = "Chikorita";
            String offeredCard2Quantity = "3";
            String offeredCard2Cost = "10";
            String wishedCard1Type = "Mew";
            String wishedCard2Type = "Bidoof";

            // Crear un DocumentBuilder
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Crear un Document
            Document doc = builder.newDocument();

            // Crear elementos y construir la estructura
            Element messageElement = doc.createElement("Message");
            doc.appendChild(messageElement);

            Element comuncIdElement = doc.createElement("comunc_id");
            comuncIdElement.appendChild(doc.createTextNode(comuncId));
            messageElement.appendChild(comuncIdElement);

            Element msgIdElement = doc.createElement("msg_id");
            msgIdElement.appendChild(doc.createTextNode(msgId));
            messageElement.appendChild(msgIdElement);

            // Crear el elemento header y agregarlo a Message
            Element headerElement = doc.createElement("header");
            messageElement.appendChild(headerElement);

            // Añadir los elementos dentro de header
            Element typeProtocolElement = doc.createElement("type_protocol");
            typeProtocolElement.appendChild(doc.createTextNode(typeProtocol));
            headerElement.appendChild(typeProtocolElement);

            Element protocolStepElement = doc.createElement("protocol_step");
            protocolStepElement.appendChild(doc.createTextNode(protocolStep));
            headerElement.appendChild(protocolStepElement);

            Element comunicationProtocolElement = doc.createElement("comunication_protocol");
            comunicationProtocolElement.appendChild(doc.createTextNode(comunicationProtocol));
            headerElement.appendChild(comunicationProtocolElement);

            Element originElement = doc.createElement("origin");
            headerElement.appendChild(originElement);

            Element originIdElement = doc.createElement("origin_id");
            originIdElement.appendChild(doc.createTextNode(originId));
            originElement.appendChild(originIdElement);

            Element originIpElement = doc.createElement("origin_ip");
            originIpElement.appendChild(doc.createTextNode(originIp));
            originElement.appendChild(originIpElement);

            Element originPortUDPElement = doc.createElement("origin_port_UDP");
            originPortUDPElement.appendChild(doc.createTextNode(originPortUDP));
            originElement.appendChild(originPortUDPElement);

            Element originPortTCPElement = doc.createElement("origin_port_TCP");
            originPortTCPElement.appendChild(doc.createTextNode(originPortTCP));
            originElement.appendChild(originPortTCPElement);

            Element originTimeElement = doc.createElement("origin_time");
            originTimeElement.appendChild(doc.createTextNode(originTime));
            originElement.appendChild(originTimeElement);

            // Crear el elemento destination y agregarlo a header
            Element destinationElement = doc.createElement("destination");
            headerElement.appendChild(destinationElement);

            Element destinationIdElement = doc.createElement("destination_id");
            destinationIdElement.appendChild(doc.createTextNode(destinationId));
            destinationElement.appendChild(destinationIdElement);

            Element destinationIpElement = doc.createElement("destination_ip");
            destinationIpElement.appendChild(doc.createTextNode(destinationIp));
            destinationElement.appendChild(destinationIpElement);

            Element destinationPortUDPElement = doc.createElement("destination_port_UDP");
            destinationPortUDPElement.appendChild(doc.createTextNode(destinationPortUDP));
            destinationElement.appendChild(destinationPortUDPElement);

            Element destinationPortTCPElement = doc.createElement("destination_port_TCP");
            destinationPortTCPElement.appendChild(doc.createTextNode(destinationPortTCP));
            destinationElement.appendChild(destinationPortTCPElement);

            Element destinationTimeElement = doc.createElement("destination_time");
            destinationTimeElement.appendChild(doc.createTextNode(destinationTime));
            destinationElement.appendChild(destinationTimeElement);

            Element bodyElement = doc.createElement("body");
            messageElement.appendChild(bodyElement);

            Element bodyInfoElement = doc.createElement("body_info");
            bodyInfoElement.appendChild(doc.createTextNode(bodyInfo));
            bodyElement.appendChild(bodyInfoElement);

            Element deathContentElement = doc.createElement("death_content");
            bodyElement.appendChild(deathContentElement);

            Element deathReasonElement = doc.createElement("death_reason");
            deathContentElement.appendChild(deathReasonElement);

            // Agrega los elementos dentro de death_reason si es necesario

            Element deathAgentInfoElement = doc.createElement("death_agent_info");
            deathContentElement.appendChild(deathAgentInfoElement);

            Element ownedCardsElement = doc.createElement("owned_cards");
            deathAgentInfoElement.appendChild(ownedCardsElement);

            Element ownedCard1Element = doc.createElement("owned_card1");
            ownedCardsElement.appendChild(ownedCard1Element);

            Element ownedCard1TypeElement = doc.createElement("owned_card_type");
            ownedCard1TypeElement.appendChild(doc.createTextNode(ownedCard1Type));
            ownedCard1Element.appendChild(ownedCard1TypeElement);

            Element ownedCard1QuantityElement = doc.createElement("owned_card_quantity");
            ownedCard1QuantityElement.appendChild(doc.createTextNode(ownedCard1Quantity));
            ownedCard1Element.appendChild(ownedCard1QuantityElement);

            Element ownedCard1CostElement = doc.createElement("owned_card_cost");
            ownedCard1CostElement.appendChild(doc.createTextNode(ownedCard1Cost));
            ownedCard1Element.appendChild(ownedCard1CostElement);

            Element ownedCard2Element = doc.createElement("owned_card2");
            ownedCardsElement.appendChild(ownedCard2Element);

            Element ownedCard2TypeElement = doc.createElement("owned_card_type");
            ownedCard2TypeElement.appendChild(doc.createTextNode(ownedCard2Type));
            ownedCard2Element.appendChild(ownedCard2TypeElement);

            Element ownedCard2QuantityElement = doc.createElement("owned_card_quantity");
            ownedCard2QuantityElement.appendChild(doc.createTextNode(ownedCard2Quantity));
            ownedCard2Element.appendChild(ownedCard2QuantityElement);

            Element ownedCard2CostElement = doc.createElement("owned_card_cost");
            ownedCard2CostElement.appendChild(doc.createTextNode(ownedCard2Cost));
            ownedCard2Element.appendChild(ownedCard2CostElement);

            Element wantedCardsElement = doc.createElement("wanted_cards");
            deathAgentInfoElement.appendChild(wantedCardsElement);

            Element wantedCard1Element = doc.createElement("wanted_card1");
            wantedCardsElement.appendChild(wantedCard1Element);

            Element wantedCard1TypeElement = doc.createElement("wanted_card_type");
            wantedCard1TypeElement.appendChild(doc.createTextNode(wantedCard1Type));
            wantedCard1Element.appendChild(wantedCard1TypeElement);

            Element wantedCard2Element = doc.createElement("wanted_card2");
            wantedCardsElement.appendChild(wantedCard2Element);

            Element wantedCard2TypeElement = doc.createElement("wanted_card_type");
            wantedCard2TypeElement.appendChild(doc.createTextNode(wantedCard2Type));
            wantedCard2Element.appendChild(wantedCard2TypeElement);

            Element ownedMoneyElement = doc.createElement("owned_money");
            ownedMoneyElement.appendChild(doc.createTextNode(ownedMoney));
            deathAgentInfoElement.appendChild(ownedMoneyElement);

            Element createdChildsElement = doc.createElement("created_childs");
            createdChildsElement.appendChild(doc.createTextNode(createdChilds));
            deathAgentInfoElement.appendChild(createdChildsElement);

            Element deathTimeElement = doc.createElement("death_time");
            deathTimeElement.appendChild(doc.createTextNode(deathTime));
            deathAgentInfoElement.appendChild(deathTimeElement);

            Element tradeContentElement = doc.createElement("trade_content");
            bodyElement.appendChild(tradeContentElement);

            Element pastTradeWantedCardElement = doc.createElement("past_trade_wanted_card");
            pastTradeWantedCardElement.appendChild(doc.createTextNode(pastTradeWantedCard));
            tradeContentElement.appendChild(pastTradeWantedCardElement);

            Element pastTradeGivenCardElement = doc.createElement("past_trade_given_card");
            pastTradeGivenCardElement.appendChild(doc.createTextNode(pastTradeGivenCard));
            tradeContentElement.appendChild(pastTradeGivenCardElement);

            Element tradeWantedCardElement = doc.createElement("trade_wanted_card");
            tradeWantedCardElement.appendChild(doc.createTextNode(tradeWantedCard));
            tradeContentElement.appendChild(tradeWantedCardElement);

            Element tradeGivenCardElement = doc.createElement("trade_given_card");
            tradeGivenCardElement.appendChild(doc.createTextNode(tradeGivenCard));
            tradeContentElement.appendChild(tradeGivenCardElement);

            Element offeredCardsElement = doc.createElement("offered_cards");
            tradeContentElement.appendChild(offeredCardsElement);

            Element offeredCard1Element = doc.createElement("offered_card1");
            offeredCardsElement.appendChild(offeredCard1Element);

            Element offeredCard1TypeElement = doc.createElement("offered_card_type");
            offeredCard1TypeElement.appendChild(doc.createTextNode(offeredCard1Type));
            offeredCard1Element.appendChild(offeredCard1TypeElement);

            Element offeredCard1QuantityElement = doc.createElement("offered_card_quantity");
            offeredCard1QuantityElement.appendChild(doc.createTextNode(offeredCard1Quantity));
            offeredCard1Element.appendChild(offeredCard1QuantityElement);

            Element offeredCard1CostElement = doc.createElement("offered_card_cost");
            offeredCard1CostElement.appendChild(doc.createTextNode(offeredCard1Cost));
            offeredCard1Element.appendChild(offeredCard1CostElement);

            Element offeredCard2Element = doc.createElement("offered_card2");
            offeredCardsElement.appendChild(offeredCard2Element);

            Element offeredCard2TypeElement = doc.createElement("offered_card_type");
            offeredCard2TypeElement.appendChild(doc.createTextNode(offeredCard2Type));
            offeredCard2Element.appendChild(offeredCard2TypeElement);

            Element offeredCard2QuantityElement = doc.createElement("offered_card_quantity");
            offeredCard2QuantityElement.appendChild(doc.createTextNode(offeredCard2Quantity));
            offeredCard2Element.appendChild(offeredCard2QuantityElement);

            Element offeredCard2CostElement = doc.createElement("offered_card_cost");
            offeredCard2CostElement.appendChild(doc.createTextNode(offeredCard2Cost));
            offeredCard2Element.appendChild(offeredCard2CostElement);

            Element wishedCardsElement = doc.createElement("wished_cards");
            tradeContentElement.appendChild(wishedCardsElement);

            Element wishedCard1Element = doc.createElement("wished_card1");
            wishedCardsElement.appendChild(wishedCard1Element);

            Element wishedCard1TypeElement = doc.createElement("wished_card_type");
            wishedCard1TypeElement.appendChild(doc.createTextNode(wishedCard1Type));
            wishedCard1Element.appendChild(wishedCard1TypeElement);

            Element wishedCard2Element = doc.createElement("wished_card2");
            wishedCardsElement.appendChild(wishedCard2Element);

            Element wishedCard2TypeElement = doc.createElement("wished_card_type");
            wishedCard2TypeElement.appendChild(doc.createTextNode(wishedCard2Type));
            wishedCard2Element.appendChild(wishedCard2TypeElement);

            Element tradeMoneyElement = doc.createElement("trade_money");
            tradeMoneyElement.appendChild(doc.createTextNode(tradeMoney));
            tradeContentElement.appendChild(tradeMoneyElement);

            Element commonContentElement = doc.createElement("common_content");
            messageElement.appendChild(commonContentElement);

            Element agentsDirectoryElement = doc.createElement("agents_directory");
            commonContentElement.appendChild(agentsDirectoryElement);

            Element acc1Element = doc.createElement("acc1");
            agentsDirectoryElement.appendChild(acc1Element);

            Element idAcc1Element = doc.createElement("id");
            idAcc1Element.appendChild(doc.createTextNode("ag1_id"));
            acc1Element.appendChild(idAcc1Element);

            Element portAcc1Element = doc.createElement("port");
            portAcc1Element.appendChild(doc.createTextNode("10"));
            acc1Element.appendChild(portAcc1Element);

            Element acc2Element = doc.createElement("acc2");
            agentsDirectoryElement.appendChild(acc2Element);

            Element idAcc2Element = doc.createElement("id");
            idAcc2Element.appendChild(doc.createTextNode("ag2_id"));
            acc2Element.appendChild(idAcc2Element);

            Element portAcc2Element = doc.createElement("port");
            portAcc2Element.appendChild(doc.createTextNode("20"));
            acc2Element.appendChild(portAcc2Element);

            Element deadAgentsElement = doc.createElement("dead_agents");
            commonContentElement.appendChild(deadAgentsElement);

            Element deadAcc1Element = doc.createElement("dead_acc1");
            deadAgentsElement.appendChild(deadAcc1Element);

            Element idDeadAcc1Element = doc.createElement("id");
            idDeadAcc1Element.appendChild(doc.createTextNode("ag3_id"));
            deadAcc1Element.appendChild(idDeadAcc1Element);

            Element deadAcc2Element = doc.createElement("dead_acc2");
            deadAgentsElement.appendChild(deadAcc2Element);

            Element idDeadAcc2Element = doc.createElement("id");
            idDeadAcc2Element.appendChild(doc.createTextNode("ag4_id"));
            deadAcc2Element.appendChild(idDeadAcc2Element);

            // Escribir el DOM en un archivo XML
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            // Configuración de la salida
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");

            File file = new File("prueba_" +comuncId+ ".xml");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(file);

            transformer.transform(source, result);

            System.out.println("Archivo XML creado correctamente.");
        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }
    }
}
