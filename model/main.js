function init() {
  const $ = go.GraphObject.make;

  myDiagram = $(
    go.Diagram,
    "myDiagramDiv", // must name or refer to the DIV HTML element
    {
      allowDelete: false,
      allowCopy: false,
      layout: $(go.ForceDirectedLayout),
      "undoManager.isEnabled": true,
    }
  );

  // COLORS
  const colors = {
    red: "#be4b15",
    green: "#52ce60",
    blue: "#6ea5f8",
    lightred: "#fd8852",
    lightblue: "#afd4fe",
    lightgreen: "#b9e986",
    pink: "#faadc1",
    purple: "#d689ff",
    orange: "#fdb400",
    gold: "#ffd700"
  };

  // ROW TEMPLATE
  const rowTemplate = $(go.Panel, "Horizontal",
    $(go.Shape,
      {
        desiredSize: new go.Size(15, 15),
        strokeJoin: "round",
        strokeWidth: 3,
        stroke: null,
        margin: 2,
      },
      new go.Binding("figure", "figure"),
      new go.Binding("fill", "color"),
      new go.Binding("stroke", "color")
    ),

    $(go.TextBlock, { stroke: "#333333", font: "bold 18px sans-serif", margin: 5},
      new go.Binding("text", "name")
    )
  );

  // TABLE TEMPLATE
  myDiagram.nodeTemplate = $(go.Node, "Auto", 
    {
        selectionAdorned: true,
        resizable: true,
        layoutConditions: go.Part.LayoutStandard & ~go.Part.LayoutNodeSized,
        fromSpot: go.Spot.AllSides,
        toSpot: go.Spot.AllSides,
        isShadowed: true,
        shadowOffset: new go.Point(3, 3),
        shadowColor: "#C5C1AA",
    },

    new go.Binding("location", "location").makeTwoWay(),

    new go.Binding("desiredSize", "visible", v => new go.Size(NaN, NaN)).ofObject("LIST"),

    $(go.Shape, "RoundedRectangle", { fill: "white", stroke: "#eeeeee", strokeWidth: 3 }),

    $(go.Panel, "Table", 
        { margin: 8, stretch: go.GraphObject.Fill },
        $(go.RowColumnDefinition, { row: 0, sizing: go.RowColumnDefinition.None }),
        $(go.TextBlock, {
            row: 0, alignment: go.Spot.Center,
            margin: new go.Margin(0, 24, 0, 2), 
            font: "bold 16px sans-serif"
        },

        new go.Binding("text", "key")),

        $("PanelExpanderButton", "LIST", { row: 0, alignment: go.Spot.TopRight }),

        $(go.Panel, "Vertical", {
            name: "LIST",
            row: 1,
            padding: 15,
            alignment: go.Spot.TopLeft,
            defaultAlignment: go.Spot.Left,
            stretch: go.GraphObject.Horizontal,
            itemTemplate: rowTemplate
        }, new go.Binding("itemArray", "items"))  
    ) 
  );

  myDiagram.linkTemplate =
  $(go.Link,  // the whole link panel
    {
      selectionAdorned: true,
      layerName: "Foreground",
      reshapable: true,
      routing: go.Link.AvoidsNodes,
      corner: 5,
      curve: go.Link.JumpOver
    },
    $(go.Shape,  // the link shape
      { stroke: "#303B45", strokeWidth: 2.5 }),
    $(go.TextBlock,  // the "from" label
      {
        textAlign: "center",
        font: "bold 14px sans-serif",
        stroke: "#1967B3",
        segmentIndex: 0,
        segmentOffset: new go.Point(NaN, NaN),
        segmentOrientation: go.Link.OrientUpright
      },
      new go.Binding("text", "text")),
    $(go.TextBlock,  // the "to" label
      {
        textAlign: "center",
        font: "bold 14px sans-serif",
        stroke: "#1967B3",
        segmentIndex: -1,
        segmentOffset: new go.Point(NaN, NaN),
        segmentOrientation: go.Link.OrientUpright
      },
      new go.Binding("text", "toText"))
  );
    
  const primaryKey = { iskey: true, figure: "Key", color: colors.gold };
  const foreignKey = { iskey: false, figure: "Key", color: colors.green };
  const number = { iskey: false, figure: "Hexagon", color: colors.blue }
  const varchar = { iskey: false, figure: "Triangle", color: colors.red }
  const char = { iskey: false, figure: "Circle", color: colors.purple }
  const date = { iskey: false, figure: "Square", color: colors.pink }

  const nodeDataArray = [
    {
      key: "appliance",
      items: [
        { name: "Id", ...primaryKey},
        { name: "appliance_code", ...number },
        { name: "appliance_name", ...varchar},
        { name: "photo_url", ...varchar}
      ]
    },
    {
        key: "part",
        items: [
            { name: "Id", ...primaryKey},
            { name: "part_code", ...number},
            { name: "part_name", ...varchar},
            { name: "price", ...number },
            { name: "amount", ...number },
            { name: "photo_url", ...varchar},
            { name: "appliande_id", ...foreignKey}
        ]
    },
    {
        key: "repair",
        items: [
            { name: "Id", ...primaryKey},
            { name: "repair_code", ...number},
            { name: "status", ...char},
            { name: "customer_id", ...foreignKey},
            { name: "appliance_id", ...foreignKey}
        ]
    },
    {
      key: "dates",
      items: [
        { name: "Id", ...primaryKey},
        { name: "register_date",  ...date},
        { name: "finish_date", ...date},
        { name: "collection_date", ...date}
      ]
    },
    {
        key: "customer",
        items: [
            { name: "Id", ...primaryKey},
            { name: "customer_code", ...number },
            { name: "customer_name", ...varchar},
            { name: "email", ...varchar},
            { name: "phone", ...varchar},
        ]
    },
    {
        key: "repair_part",
        items: [
            { name: "Id", ...primaryKey},
            { name: "repair_id", ...foreignKey},
            { name: "part_id", ...foreignKey}
        ]
    },
    {
        key: "Legenda",
        items: [
            { name: "Primary Key", ...primaryKey },
            { name: "Foreign Key", ...foreignKey },
            { name: "NUMBER", ...number },
            { name: "VARCHAR2", ...varchar },
            { name: "CHAR(1)", ...char },
            { name: "DATE", ...date }
        ]
    },
  ];

  const linkDataArray = [
    { from: "appliance", to: "part", text: "1", toText: "N" },
    { from: "appliance", to: "repair", text: "1", toText: "N" },
    { from: "customer", to: "repair", text: "1", toText: "N" },
    { from: "repair", to: "repair_part", text: "1", toText: "N" },
    { from: "part", to: "repair_part", text: "1", toText: "N" },
    { from: "repair", to: "dates", text: "1", toText: "1" },
  ];

  myDiagram.model = new go.GraphLinksModel(
    {
      copiesArrays: true,
      copiesArrayObjects: true,
      nodeDataArray: nodeDataArray,
      linkDataArray: linkDataArray
    });
}

window.addEventListener("DOMContentLoaded", init);
