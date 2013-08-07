package datatax;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity public class DtHistoricoTicket {

	@Id @GeneratedValue protected @Getter @Setter Long id;
	@ManyToOne @JoinColumn(name = "TICKET_ID") protected @Getter @Setter DtTicket ticket;
	@ManyToOne protected @Getter @Setter DtSituacaoTicket situacaoTicket;
	@Column(name = "DATA_SITUACAO") protected @Getter @Setter Date dataSituacao;
	@Column(name = "USUARIO_ID") protected @Getter Long idUsuario;

}
