package br.jus.trt.app.platao.controller;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.jus.trt.app.platao.business.domain.Servidor;
import br.jus.trt.app.platao.business.facade.ServidorCrudFacade;
import br.jus.trt.lib.qbe.api.Filter;
import br.jus.trt6.lib.common_web.action.CrudActionBase;

/**
 * Clase de controle para atender às requisições da tela de cadastro de servidor.
 * @author augusto
 *
 */
@SuppressWarnings("serial")

@Named
@ConversationScoped
public class ServidorAction extends CrudActionBase<Servidor, Long, ServidorCrudFacade> {
	
	@Inject
	private Conversation conversation;
	
	@Inject
	private CidadeUfController cidadeUfController;
	
	@Override
	public void init() {
		super.init();
		
		conversation.begin();
		search();
	}

	@Override
	protected void configSearch(Filter<? extends Servidor> filter) {
		super.configSearch(filter);
		fetchDependencies(filter);
	}

	/**
	 * Configura um filtro QBE com os fetches básicos necessários para carregar um Servidor
	 */
	private void fetchDependencies(Filter<? extends Servidor> filter) {
		filter.addFetch("cidade.uf");
	}

	@Override
	protected void preLoad(Servidor entidade) {
		super.preLoad(entidade);
		
		// prepara a lista de CidadeUf
		cidadeUfController.setSelectedUf(entidade.getCidade().getUf());
		cidadeUfController.loadCidades();
	}
	
	@Override
	protected void configLoad(Servidor entidade, Filter<Servidor> loadFilter) {
		super.configLoad(entidade, loadFilter);
		fetchDependencies(loadFilter);
	}
	
	public CidadeUfController getCidadeUfController() {
		return cidadeUfController;
	}
	
}
