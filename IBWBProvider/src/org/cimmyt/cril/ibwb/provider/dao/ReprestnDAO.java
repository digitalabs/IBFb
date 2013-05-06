package org.cimmyt.cril.ibwb.provider.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.cimmyt.cril.ibwb.api.dao.AbstractDAO;
import org.cimmyt.cril.ibwb.api.dao.utils.ValidatingDataType;
import org.cimmyt.cril.ibwb.domain.Represtn;
import org.cimmyt.cril.ibwb.domain.Steffect;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.HibernateCallback;

public class ReprestnDAO extends AbstractDAO<Represtn, Integer> {

    public ReprestnDAO() {
        super(Represtn.class);
    }

    @Override
    public Represtn prepareToCreate(Represtn represtn) {
        if (isLocal()) {
            represtn.setRepresno(getNextMin());
        }
        if (isCentral()) {
            represtn.setRepresno(getNextMax());
        }
        return represtn;
    }

    @Override
    protected void buildCriteria(DetachedCriteria criteria, Represtn filter) {
        // TODO Auto-generated method stub
    }

    @Override
    public String getKeyProperty() {
        return "represno";
    }

    @Override
    public String getConsulta(Represtn filtro) {
        String query = "from Represtn as r";

        if (filtro.getGlobalsearch() == null) {
            setQuery("r.represno", filtro.getRepresno());
            setQuery("r.effectid", filtro.getEffectid());
            setQuery("r.represname", filtro.getRepresname());
        } else {
            global = true;
            if (ValidatingDataType.isNumeric(filtro.getGlobalsearch())) {
                setQuery("r.represno", Integer.valueOf(filtro.getRepresno()));
                setQuery("r.effectid", Integer.valueOf(filtro.getEffectid()));
                setQueryInTo("r.represname", filtro.getRepresname());
            } else {
                setQueryInTo("r.represname", filtro.getRepresname());
            }
        }
        return query;
    }

    /**
     * Gests a Representation object for a Study
     * @param studyId Study number
     * @param represName Representation name to find
     * @return
     */
    public Represtn getReprestnForStudyId(final Integer studyId, String represName) {
        Represtn represtn = null;

        String queryString = "from Represtn as r where r.effectid in " +
            " (select se.effectid from  Steffect as se where se.studyid = ? and se.effectname = ? ) ";
        
        Object[] params = new Object[2];
        params[0] = studyId;
        params[1] = represName;
        
        List<Represtn> represtnList = getHibernateTemplate().find(queryString, params);
        
        if (!represtnList.isEmpty()) {
            represtn = represtnList.get(0);
        }

        return represtn;
    }
}