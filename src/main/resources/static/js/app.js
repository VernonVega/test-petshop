Ext.onReady(function () {

  Ext.define('evgkit.model.Client', {
    extend: 'Ext.data.Model',
    fields: [
      {name: 'clientId', type: 'int'},
      {name: 'clientName', type: 'string'},
      {name: 'clientPhone', type: 'string'},
      {name: 'clientEmail', type: 'string'},
      {name: 'clientIsWholesale', type: 'int'},
      {name: 'clientAddress', type: 'string'}
    ]
  });

  Ext.define('evgkit.store.Clients', {
    extend: 'Ext.data.Store',
    model: 'evgkit.model.Client',
    fields: ['clientId', 'clientName', 'clientPhone', 'clientEmail', 'clientIsWholesale'],
    proxy: {
      type: 'ajax',
      url: '/api/v1/clients',
      method: 'GET',
      reader: {
        type: 'json',
        root: 'clients'
      }
    },
    autoLoad: true
  });

  Ext.define('evgkit.view.ClientsList', {
    extend: 'Ext.grid.Panel',
    alias: 'widget.clientslist',
    title: 'Client List',
    store: 'Clients',
    initComponent: function () {
      this.tbar = [{
        text: 'Add Client',
        action: 'add',
        iconCls: 'client-add'
      },
        {
          text: 'Refresh Client',
          action: 'refresh',
          iconCls: 'client-add'
        }
      ];
      this.columns = [
        {
          header: '# Клиента',
          dataIndex: 'clientId',
          flex: 1
        },
        {
          header: 'Имя',
          dataIndex: 'clientName',
          // flex: 1
        },
        {
          header: 'Телефон',
          dataIndex: 'clientPhone',
          // flex: 1
        },
        {
          header: 'Имейл',
          dataIndex: 'clientEmail',
          // flex: 1
        },
        {
          header: 'Возраст',
          dataIndex: 'age',
          // flex: 1
        },
        {
          header: 'Опт/Розн',
          dataIndex: 'clientIsWholesale',
          flex: 1,
          xtype: 'booleancolumn',
          trueText: 'Yes',
          falseText: 'No',
        },
        {
          header: 'Город',
          dataIndex: 'clientAddress',
          // flex: 1
        },
        {
          header: 'Action', width: 50, renderer: function (v, m, r) {
            var id = Ext.id();
            var max = 15;
            Ext.defer(function () {
              Ext.widget('image', {
                renderTo: id,
                name: 'delete',
                src: 'images/client_delete.png',
                listeners: {
                  afterrender: function (me) {
                    me.getEl().on('click', function () {
                      var grid = Ext.ComponentQuery.query('clientslist')[0];
                      if (grid) {
                        var sm = grid.getSelectionModel();
                        var rs = sm.getSelection();
                        if (!rs.length) {
                          Ext.Msg.alert('Info', 'No Client Selected');
                          return;
                        }
                        Ext.Msg.confirm('Remove Client',
                          'Are you sure you want to delete?',
                          function (button) {
                            if (button === 'yes') {
                              var client = rs[0].getData();
                              Ext.Ajax.request({
                                url: '/api/v1/clients',
                                method: 'DELETE',
                                jsonData: client,
                                success: function (response) {
                                  var grid = Ext.ComponentQuery.query('clientslist')[0];
                                  grid.getStore().load();
                                }
                              });
                            }
                          });
                      }
                    });
                  }
                }
              });
            }, 50);
            return Ext.String.format('<div id="{0}"></div>', id);
          }
        }
      ];
      this.callParent(arguments);
    }
  });

  Ext.define('evgkit.view.ClientsForm', {
    extend: 'Ext.window.Window',
    alias: 'widget.clientsform',
    title: 'Add Client',
    width: 450,
    layout: 'fit',
    resizable: false,
    closeAction: 'hide',
    modal: true,
    config: {
      recordIndex: 0,
      action: ''
    },
    items: [{
      xtype: 'form',
      layout: 'anchor',
      bodyStyle: {
        background: 'none',
        padding: '10px',
        border: '0'
      },
      defaults: {
        xtype: 'textfield',
        anchor: '100%'
      },
      items: [
      // {
      //   name: 'clientId',
      //   fieldLabel: 'clientId',
      // },
      {
        name: 'clientName',
        fieldLabel: 'Имя',
      },
      {
        name: 'clientPhone',
        fieldLabel: 'Телефон',
      },
      {
        name: 'clientEmail',
        fieldLabel: 'Имейл',
      },
      {
        name: 'age',
        fieldLabel: 'Возраст',
      },
      {
        name: 'clientIsWholesale',
        fieldLabel: 'Опт/Розн',
        xtype: 'checkboxfield',
        inputValue: 'true',
        uncheckedValue: 'false'
      },
      {
        name: 'clientCity',
        fieldLabel: 'Город',
      }
      ]
    }],
    buttons: [{
      text: 'OK',
      action: 'add'
    }, {
      text: 'Reset',
      handler: function () {
        this.up('window').down('form').getForm().reset();
      }
    }, {
      text: 'Cancel',
      handler: function () {
        this.up('window').close();
      }
    }]
  });

  Ext.define('evgkit.controller.Clients', {
      extend: 'Ext.app.Controller',
      stores: ['Clients'],
      views: ['ClientsList', 'ClientsForm'],
      refs: [{
    ref: 'formWindow',
      xtype: 'clientsform',
      selector: 'clientsform',
      autoCreate: true
    }],
    init: function () {
      this.control({
        'clientslist > toolbar > button[action=add]': {
          click: this.showAddForm
        },
        'clientslist': {
          itemdblclick: this.onRowdblclick
        },
        'clientsform button[action=add]': {
          click: this.doAddClient
        },
        'clientslist > toolbar > button[action=refresh]': {
          // click: this.showAllClients
          click:  this.refreshClient
        }
      });
    },
    onRowdblclick: function (me, record, item, index) {
      var win = this.getFormWindow();
      win.setTitle('Edit Client');
      win.setAction('edit');
      win.setRecordIndex(index);
      win.down('form').getForm().setValues(record.getData());
      win.show();
    },
    showAddForm: function () {
      var win = this.getFormWindow();
      win.setTitle('Add Client');
      win.setAction('add');
      win.down('form').getForm().reset();
      win.show();
    },
    doAddClient: function () {
      var win = this.getFormWindow();
      var store = this.getClientsStore();
      var values = win.down('form').getValues();

      var action = win.getAction();
      var url = '/api/v1/clients';
      var method = 'POST';
      if (action === 'edit') {
        method = 'PUT';
      }
      Ext.Ajax.request({
        url: url,
        method: method,
        jsonData: values,
        success: function (response) {
          store.load();
        }
      });
      // win.close();
    },

    // showAllClients: function () {
    //   var win = this.getFormWindow();
    //   // win.setTitle('Add Client');
    //   win.setAction('refresh');
    //   win.down('form').getForm().reset();
    //   this.refreshClient;
    // },
    refreshClient: function () {
      var win = this.getFormWindow();
      var store = this.getClientsStore();
      var values = win.down('form').getValues();

      var url = '/api/v1/clients';
      var method = 'GET';

      Ext.Ajax.request({
        url: url,
        method: method,
        jsonData: values,
        success: function (response) {
          store.load();
        }
      });
      // win.close();
    },
  });

  Ext.application({
    name: 'evgkit',
    controllers: ['Clients'],
    launch: function () {
      Ext.widget('clientslist', {
        width: 900,
        height: 400,
        renderTo: 'output'
      });
    }
  });
});