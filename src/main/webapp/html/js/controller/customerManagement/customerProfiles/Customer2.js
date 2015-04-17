Ext.define('CRM.controller.customerManagement.customerProfiles.Customer2', {
    extend: 'Ext.app.Controller',
    views: [ 'customerManagement.customerProfiles.CustomerTabInfo', 
             'customerManagement.customerProfiles.CustomerTab' ],
    stores: [ 'customerManagement.customerProfiles.Customer' ],
    init: function() {
        this.control({
            'customertab': {
                tabchange: this.tabchange
            }
        });
    },
    viewInit: function(record) {
        var customertab = Ext.getCmp('customertabinfo');
        if (typeof (customertab) !== 'undefined') {
            customertab.destroy();
        }
        this.record = record;
        customertab = Ext.widget('customertabinfo');
        customertab.down("form").loadRecord(record);
        Ext.getCmp('centercard').insert(1, customertab);
        Ext.getCmp('centercard').getLayout().setActiveItem('customertabinfo');
        initController('contactManagement.contactProfiles.Contact').viewInit(211, Ext.getCmp('customercontacttab'), this.record);
    },
    tabchange: function(card, previous) {
        var id = previous.id;
        if (id === 'customercontacttab') {
            initController('contactManagement.contactProfiles.Contact').viewInit(211, previous, this.record);
        } else if (id === 'customerlianxitab') {
            initController('customerManagement.contactTrack.ContactController').viewInit(121, previous, this.record);
        } else if (id ==='customerhistorytab') {
            initController('customerManagement.contactTrack.ContactHistory').viewInit(122, previous, this.record);
        } else if (id ==='leaderadvicetab') {
            initController('customerManagement.customerProfiles.LeaderAdvice').viewInit(114, previous, this.record);
        }else if (id ==='businesstab') {
            initController('customerManagement.customerProfiles.Business').viewInit(999, previous, this.record);
        }
    }
});