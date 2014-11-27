package ponkin.glu.pp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

/**
 * @author Alexey Ponkin
 * @version 1, 27 Nov 2014
 */
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
public class Pong {

    private BigDecimal n;

}
