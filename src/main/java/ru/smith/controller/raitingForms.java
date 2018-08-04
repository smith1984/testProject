package ru.smith.controller;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.primefaces.model.chart.PieChartModel;
import ru.smith.util.HibernateUtil;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@ManagedBean
@RequestScoped
public class raitingForms {

    private PieChartModel pieModel;


    @PostConstruct
    public void init() {
        createPieModel();
    }

    public PieChartModel getPieModel() {
        return pieModel;
    }


    private void createPieModel() {
        pieModel = new PieChartModel();

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query query = session.getNamedQuery("ratingForms").setMaxResults(5);
        List<Object[]> objectList = query.list();

        query = session.getNamedQuery("countUseForm");
        AtomicLong count = new AtomicLong((long) query.list().get(0));

        objectList.forEach(objects -> {
            pieModel.set((objects[2] + "\n" + objects[1]), (long) objects[0]);
            count.addAndGet(-(long) objects[0]);
        });
        pieModel.set("Другие формы", count);
        pieModel.setTitle("Рейтинг форм");
        pieModel.setShowDataLabels(true);
        pieModel.setLegendPosition("w");

    }
}
