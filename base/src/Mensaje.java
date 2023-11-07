import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

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

    protected String ID_mensaje; // IP del agente que envía el mensaje
    protected String IP_origen; // IP del agente que envía el mensaje
    protected int puerto_origen; // Puerto del agente que envía el mensaje
    protected String id_origen; // Identificador único del agente que envía el mensaje

    protected String IP_destino; // IP del agente destino de este mensaje
    protected int puerto_destino; // Puerto del agente destino de este mensaje.
                                    // OJO, el "puerto_destino" es el puerto asociado al agente destinatarios (esto es su "Puerto_Propio")
                                    // al realizar el envio, la calse "Enviar" se encarga de direccionarlo hacia "Puerto_Propio_TCP" o "Puerto_Propio_UDP"
                                    // segun proceda atendiendo al valor de la propiedad "protocolo" de este objeto
    protected String id_destino; // Identificador único del agente destino de este mensaje
    protected String protocolo;  // Se admiten protocolos TCP y UDP
    protected String cuerpo_del_mensaje;  // Contenido del mensaje a enviar. s el contenido de este campo, lo que viajara al destino

//CONSTRUCTOR DE MENSAJE
    public Mensaje(String ID_mensaje,
                    String IP_origen,
                    int puerto_origen,
                    String id_origen,
                    String IP_destino,
                    int puerto_destino,
                    String id_destino,
                    String protocolo,
                    String cuerpo_del_mensaje)
    {
        this.ID_mensaje = ID_mensaje;
        this.IP_origen = IP_origen;
        this.puerto_origen = puerto_origen;
        this.id_origen = id_origen;
        this.IP_destino = IP_destino;
        this.puerto_destino = puerto_destino;
        this.id_destino = id_destino;
        this.protocolo = protocolo;
        this.cuerpo_del_mensaje = cuerpo_del_mensaje;

    }

    //GETTERS
    public String getCuerpo_del_mensaje() {
        return cuerpo_del_mensaje;
    }
    public String getProtocolo() {
        return protocolo;
    }

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

            //TODO: modificar para que sea un array y te los vaya introduciendo en el xml
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

            File file = new File("archivo.xml");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(file);

            transformer.transform(source, result);

            System.out.println("Archivo XML creado correctamente.");
        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }
    }
}
