package br.jus.trt.app.platao.business.facade;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.jus.trt.app.platao.business.domain.Servidor;
import br.jus.trt.app.platao.integration.persistence.ServidorRepository;
import br.jus.trt.lib.common_core.business.facade.CrudFacadeBase;

public class ServidorCrudFacade extends CrudFacadeBase<ServidorRepository, Servidor, Long> {

	@Inject
	private EntityManager em;
	
	@Override
	public Servidor saveAndFlush(Servidor entity) {
		// TODO Auto-generated method stub
		Servidor saveAndFlush = super.saveAndFlush(entity);
		
		em.flush();
		
		return saveAndFlush;
	}
	
}
